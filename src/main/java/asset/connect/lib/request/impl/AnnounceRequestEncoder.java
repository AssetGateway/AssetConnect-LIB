package asset.connect.lib.request.impl;

import asset.connect.api.request.impl.AnnounceRequest;
import asset.connect.lib.request.RequestEncoder;

public class AnnounceRequestEncoder implements RequestEncoder<AnnounceRequest> {

	public String encode(AnnounceRequest request) {
		if(request.getIp() == null) {
			return Integer.toString(request.getPort());
		}
		return request.getIp() + " " + request.getPort();
	}

	public String getLabel() {
		return "ANNOUNCE";
	}

	public Class<AnnounceRequest> getRequest() {
		return AnnounceRequest.class;
	}

}
