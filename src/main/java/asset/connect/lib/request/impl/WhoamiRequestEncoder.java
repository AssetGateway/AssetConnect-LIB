package asset.connect.lib.request.impl;

import asset.connect.api.request.impl.WhoamiRequest;
import asset.connect.lib.request.RequestEncoder;

public class WhoamiRequestEncoder implements RequestEncoder<WhoamiRequest> {

	public String encode(WhoamiRequest request) {
		return "";
	}

	public String getLabel() {
		return "WHOAMI";
	}

	public Class<WhoamiRequest> getRequest() {
		return WhoamiRequest.class;
	}

}
