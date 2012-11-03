package asset.connect.lib.request.impl;

import asset.connect.api.request.impl.KeyRequest;
import asset.connect.lib.request.RequestEncoder;

public class KeyRequestEncoder implements RequestEncoder<KeyRequest> {

	public String encode(KeyRequest request) {
		return "";
	}

	public String getLabel() {
		return "KEY";
	}

	public Class<KeyRequest> getRequest() {
		return KeyRequest.class;
	}

}
