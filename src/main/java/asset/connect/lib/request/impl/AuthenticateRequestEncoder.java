package asset.connect.lib.request.impl;

import asset.connect.api.request.impl.AuthenticateRequest;
import asset.connect.lib.request.RequestEncoder;

public class AuthenticateRequestEncoder implements RequestEncoder<AuthenticateRequest> {

	public String encode(AuthenticateRequest request) {
		return request.getUsername() + " " + request.getPassword();
	}

	public String getLabel() {
		return "AUTHENTICATE";
	}

	public Class<AuthenticateRequest> getRequest() {
		return AuthenticateRequest.class;
	}

}
