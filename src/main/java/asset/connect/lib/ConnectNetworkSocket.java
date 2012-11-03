package asset.connect.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectNetworkSocket extends Socket {

	private BufferedWriter bufferedWriter;
	private BufferedReader bufferedReader;
	private ReentrantLock writeLock = new ReentrantLock();

	public ConnectNetworkSocket(InetSocketAddress outboundAddress, String inboundIp) throws UnknownHostException, IOException {
		super();
		super.setSoTimeout(10000);
		super.bind(new InetSocketAddress(InetAddress.getByName(inboundIp), 0));
		super.connect(outboundAddress);
		this.bufferedReader = new BufferedReader(new InputStreamReader(this.getInputStream()));
		this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.getOutputStream()));
	}

	public String read() throws IOException {
		return this.bufferedReader.readLine();
	}

	public void write(String line) throws IOException {
		this.writeLock.lock();
		try {
			this.bufferedWriter.append(line);
			this.bufferedWriter.append("\r\n");
			this.bufferedWriter.flush();
		} finally {
			this.writeLock.unlock();
		}
	}

}
