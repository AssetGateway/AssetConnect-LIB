package asset.connect.lib.result.impl;

import asset.connect.api.result.StatusCode;
import asset.connect.api.result.impl.AuthenticateResult;
import asset.connect.lib.result.ResultDecoder;

public class AuthenticateResultDecoder implements ResultDecoder<AuthenticateResult> {

	public AuthenticateResult decode(String string) {
		String[] stringSplit = string.split(" ");
		if(stringSplit[0].equals("SUCCESS")) {
			return new AuthenticateResult(StatusCode.SUCCESS);
		}
		if(stringSplit[0].equals("ERROR")) {
			return new AuthenticateResult(StatusCode.valueOf(stringSplit[1]));
		}
		return null;
	}

	public String getLabel() {
		return "AUTHENTICATE";
	}

}
