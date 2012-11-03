package asset.connect.lib.request;

import java.util.HashMap;
import java.util.Map;

import asset.connect.api.request.Request;

public class RequestEncoderRegistry {

	private Map<Class<? extends Request<?>>, RequestEncoder<?>> registryByRequest = new HashMap<Class<? extends Request<?>>, RequestEncoder<?>>();
	private Map<String, RequestEncoder<?>> registryByLabel = new HashMap<String, RequestEncoder<?>>();
	
	public void submit(RequestEncoder<?> requestEncoder) {
		if(requestEncoder == null) {
			return;
		}
		this.registryByRequest.put(requestEncoder.getRequest(), requestEncoder);
		this.registryByLabel.put(requestEncoder.getLabel(), requestEncoder);
	}
	
	public RequestEncoder<?> getByLabel(String label) {
		return this.registryByLabel.get(label.toUpperCase());
	}
	
	public RequestEncoder<?> getByRequest(Class<?> request) {
		return this.registryByRequest.get(request);
	}
	
}
