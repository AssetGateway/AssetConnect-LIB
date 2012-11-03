package asset.connect.lib.result.impl;

import asset.connect.api.result.impl.KeyResult;
import asset.connect.lib.result.ResultDecoder;

public class KeyResultDecoder implements ResultDecoder<KeyResult> {

	public KeyResult decode(String string) {
		return new KeyResult(string);
	}

	public String getLabel() {
		return "KEY";
	}

}
