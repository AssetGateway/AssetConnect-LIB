package asset.connect.lib.request;

import asset.connect.api.request.Request;

public interface RequestEncoder<T extends Request<?>> {

	public String encode(T request);
	
	public String getLabel();
	
	public Class<T> getRequest();
	
}
