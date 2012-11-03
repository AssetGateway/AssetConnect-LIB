package asset.connect.lib.request.impl;

import asset.connect.api.request.impl.RedirectRequest;
import asset.connect.lib.request.RequestEncoder;

public class RedirectRequestEncoder implements RequestEncoder<RedirectRequest> {

	public String encode(RedirectRequest request) {
		return request.getPlayer() + " " + request.getServer();
	}

	public String getLabel() {
		return "REDIRECT";
	}

	public Class<RedirectRequest> getRequest() {
		return RedirectRequest.class;
	}

}
