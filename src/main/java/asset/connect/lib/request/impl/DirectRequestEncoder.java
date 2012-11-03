package asset.connect.lib.request.impl;

import asset.connect.api.request.impl.DirectRequest;
import asset.connect.lib.request.RequestEncoder;
import asset.connect.lib.util.StringUtils;

public class DirectRequestEncoder implements RequestEncoder<DirectRequest> {

	public String encode(DirectRequest request) {
		return StringUtils.join(request.getUsernames(), ";") + " " + request.getMessage();
	}

	public String getLabel() {
		return "DIRECT";
	}

	public Class<DirectRequest> getRequest() {
		return DirectRequest.class;
	}

}
