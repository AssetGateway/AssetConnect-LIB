package asset.connect.lib.result.impl;

import asset.connect.api.result.StatusCode;
import asset.connect.api.result.impl.DirectResult;
import asset.connect.lib.result.ResultDecoder;

public class DirectResultDecoder implements ResultDecoder<DirectResult> {

	public DirectResult decode(String string) {
		String[] stringSplit = string.split(" ");
		if(stringSplit[0].equals("SUCCESS")) {
			return new DirectResult(StatusCode.SUCCESS);
		}
		if(stringSplit[0].equals("ERROR")) {
			return new DirectResult(StatusCode.valueOf(stringSplit[1]));
		}
		return null;
	}

	public String getLabel() {
		return "DIRECT";
	}

}
