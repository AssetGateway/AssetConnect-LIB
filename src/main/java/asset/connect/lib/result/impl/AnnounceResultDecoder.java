package asset.connect.lib.result.impl;

import asset.connect.api.result.StatusCode;
import asset.connect.api.result.impl.AnnounceResult;
import asset.connect.lib.result.ResultDecoder;

public class AnnounceResultDecoder implements ResultDecoder<AnnounceResult> {

	public AnnounceResult decode(String string) {
		String[] stringSplit = string.split(" ");
		if(stringSplit[0].equals("SUCCESS")) {
			return new AnnounceResult(StatusCode.SUCCESS);
		}
		if(stringSplit[0].equals("ERROR")) {
			return new AnnounceResult(StatusCode.valueOf(stringSplit[1]));
		}
		return null;
	}

	public String getLabel() {
		return "ANNOUNCE";
	}

}
