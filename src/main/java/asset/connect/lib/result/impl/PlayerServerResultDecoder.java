package asset.connect.lib.result.impl;

import asset.connect.api.result.StatusCode;
import asset.connect.api.result.impl.PlayerServerResult;
import asset.connect.lib.result.ResultDecoder;

public class PlayerServerResultDecoder implements ResultDecoder<PlayerServerResult> {

	public PlayerServerResult decode(String string) {
		String[] stringSplit = string.split(" ");
		if(stringSplit[0].equals("ERROR")) {
			return new PlayerServerResult(StatusCode.valueOf(stringSplit[1]));
		}
		return new PlayerServerResult(string);
	}

	public String getLabel() {
		return "PLAYER_SERVER";
	}

}
