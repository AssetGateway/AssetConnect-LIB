package asset.connect.lib.result.impl;

import asset.connect.api.result.impl.WhoamiResult;
import asset.connect.lib.result.ResultDecoder;

public class WhoamiResultDecoder implements ResultDecoder<WhoamiResult> {

	public WhoamiResult decode(String string) {
		return new WhoamiResult(string);
	}

	public String getLabel() {
		return "WHOAMI";
	}

}
