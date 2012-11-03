package asset.connect.lib.result.impl;

import asset.connect.api.result.StatusCode;
import asset.connect.api.result.impl.RedirectResult;
import asset.connect.lib.result.ResultDecoder;

public class RedirectResultDecoder implements ResultDecoder<RedirectResult> {

	public RedirectResult decode(String string) {
		String[] stringSplit = string.split(" ");
		if(stringSplit[0].equals("SUCCESS")) {
			return new RedirectResult(StatusCode.SUCCESS);
		}
		if(stringSplit[0].equals("ERROR")) {
			return new RedirectResult(StatusCode.valueOf(stringSplit[1]));
		}
		return null;
	}

	public String getLabel() {
		return "REDIRECT";
	}

}
