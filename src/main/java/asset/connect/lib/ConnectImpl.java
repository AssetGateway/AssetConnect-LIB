package asset.connect.lib;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import asset.connect.api.Connect;
import asset.connect.api.ConnectSettings;
import asset.connect.api.DirectEvent;
import asset.connect.api.DirectEventListener;
import asset.connect.api.request.Request;
import asset.connect.api.request.RequestException;
import asset.connect.api.result.FutureResult;
import asset.connect.api.result.Result;
import asset.connect.lib.request.ConnectRequestEncoderRegistry;
import asset.connect.lib.request.RequestEncoder;
import asset.connect.lib.result.FutureResultImpl;

public class ConnectImpl implements Connect {

	private ConnectNetworkSocket networkSocket;
	private ConnectNetworkReader networkReader;

	private ExecutorService executorService;
	private ConnectSettings settings;
	private String inboundIp;

	private Set<DirectEventListener> directEventListeners = new HashSet<DirectEventListener>();
	private ReentrantLock directEventListenersLock = new ReentrantLock();

	@SuppressWarnings("rawtypes")
	private Map<Class<? extends Result>, Queue<FutureResultImpl>> pendingFutures = new HashMap<Class<? extends Result>, Queue<FutureResultImpl>>();

	private boolean closed;

	public ConnectImpl(ExecutorService executorService, ConnectSettings connectSettings) {
		this(executorService, connectSettings, "0.0.0.0");
	}

	public ConnectImpl(ExecutorService executorService, ConnectSettings connectSettings, String inboundIp) {
		this.executorService = executorService;
		this.settings = connectSettings;
		this.inboundIp = inboundIp;
	}

	public void connect() throws Exception {
		this.disconnect();
		try {
			this.networkSocket = new ConnectNetworkSocket(this.settings.getOutboundAddress(), this.inboundIp);
			this.networkReader = new ConnectNetworkReader(this, this.networkSocket);
			this.networkReader.start();
		} catch(Exception exception) {
			throw exception;
		}
	}

	@SuppressWarnings("rawtypes")
	public void disconnect() {
		try {
			if(this.pendingFutures != null) {
				for(Queue<FutureResultImpl> pendingFutures : this.pendingFutures.values()) {
					while(!pendingFutures.isEmpty()) {
						pendingFutures.poll().cancel();
					}
				}
			}
			if(this.networkReader != null) {
				this.networkReader.stop();
			}
			if(this.networkSocket != null && !this.networkSocket.isClosed()) {
				this.networkSocket.close();
			}
		} catch(Exception exception) {
			//exception.printStackTrace(); ignore stack traces
		} finally {
			this.networkSocket = null;
			this.networkReader = null;
		}
	}

	public void close() {
		this.disconnect();
		this.closed = true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends Result> FutureResult<T> request(Request<T> request) throws RequestException {
		if(this.isClosed()) {
			throw new RequestException("closed");
		}
		if(!this.isConnected()) {
			throw new RequestException("notconnected");
		}
		if(!this.pendingFutures.containsKey(request.getResult())) {
			this.pendingFutures.put(request.getResult(), new LinkedBlockingQueue<FutureResultImpl>());
		}

		FutureResultImpl<T> futureResult = new FutureResultImpl<T>();
		this.pendingFutures.get(request.getResult()).add(futureResult);

		RequestEncoder requestEncoder = ConnectRequestEncoderRegistry.INSTANCE.getByRequest(request.getClass());
		try {
			this.networkSocket.write(requestEncoder.getLabel() + " " + requestEncoder.encode(request));
		} catch(Exception exception) {
			throw new RequestException("errorwrite");
		}
		return futureResult;
	}

	public void registerDirectEventListener(DirectEventListener directEventListener) {
		this.directEventListenersLock.lock();
		try {
			this.directEventListeners.add(directEventListener);
		} finally {
			this.directEventListenersLock.unlock();
		}
	}

	public void unregisterDirectEventListener(DirectEventListener directEventListener) {
		this.directEventListenersLock.lock();
		try {
			this.directEventListeners.remove(directEventListener);
		} finally {
			this.directEventListenersLock.unlock();
		}
	}

	public void dispatchDirectEvent(final DirectEvent directEvent) {
		this.executorService.execute(new Runnable() {
			public void run() {
				directEventListenersLock.lock();
				try {
					Iterator<DirectEventListener> directEventListenersIt = directEventListeners.iterator();
					while(directEventListenersIt.hasNext()) {
						directEventListenersIt.next().onDirect(ConnectImpl.this, directEvent);
					}
				} finally {
					directEventListenersLock.unlock();
				}
			}
		});
	}

	public void dispatchResult(final Result result) {
		final FutureResultImpl<?> futureResult = this.pendingFutures.get(result.getClass()).poll();
		this.executorService.execute(new Runnable() {
			public void run() {
				futureResult.notifyResult(result);
			}
		});
	}

	public boolean isConnected() {
		return this.networkSocket != null && this.networkReader != null && System.currentTimeMillis() - this.networkReader.getLastRead() < 10000L;
	}

	public boolean isClosed() {
		return this.closed;
	}

	public ConnectSettings getSettings() {
		return this.settings;
	}

	@Deprecated
	public ConnectSettings getConnectSettings() {
		return this.settings;
	}

}
