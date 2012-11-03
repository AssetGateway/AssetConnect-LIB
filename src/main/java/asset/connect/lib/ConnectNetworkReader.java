package asset.connect.lib;

import asset.connect.api.DirectEvent;
import asset.connect.lib.result.ConnectResultDecoderRegistry;

public class ConnectNetworkReader implements Runnable {

	private ConnectImpl connect;
	private ConnectNetworkSocket connectNetworkSocket;
	private Thread thread;

	private long lastRead;

	public ConnectNetworkReader(ConnectImpl connect, ConnectNetworkSocket connectNetworkSocket) {
		this.connect = connect;
		this.connectNetworkSocket = connectNetworkSocket;
	}

	public void start() {
		if(this.thread != null) {
			throw new IllegalStateException("Already started");
		}
		this.thread = new Thread(this);
		this.thread.setName("connect-network-reader");
		this.thread.start();
		this.lastRead = System.currentTimeMillis();
	}

	public void stop() {
		if(this.thread == null) {
			throw new IllegalStateException("Already stopped");
		}
		Thread thread = this.thread;
		this.thread = null;
		thread.interrupt();
	}

	public void run() {
		try {
			Thread.sleep(0L);
			do {
				String string = this.connectNetworkSocket.read();
				if(string == null) {
					break;
				}
				this.lastRead = System.currentTimeMillis();
				string = string.trim();
				String[] stringSplit = string.split(" ");
				if(stringSplit[0].equals("PING")) {
					this.connectNetworkSocket.write("PONG");
					continue;
				}
				if(stringSplit[0].startsWith("DIRECT") && !(stringSplit[1].equals("SUCCESS") || stringSplit[1].equals("ERROR"))) {
					this.connect.dispatchDirectEvent(new DirectEvent(stringSplit[1], string.substring(stringSplit[0].length() + stringSplit[1].length() + 2)));
					continue;
				}
				this.connect.dispatchResult(ConnectResultDecoderRegistry.INSTANCE.getByLabel(stringSplit[0]).decode(string.substring(stringSplit[0].length() + 1)));
			} while(this.thread != null);
		} catch(InterruptedException exception) {
			//ignore
		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {
			this.connect.disconnect();
		}
	}
	
	public long getLastRead() {
		return this.lastRead;
	}

}
