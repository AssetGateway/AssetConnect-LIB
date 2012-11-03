package asset.connect.lib.result.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asset.connect.api.result.StatusCode;
import asset.connect.api.result.impl.ServerPlayersResult;
import asset.connect.lib.result.ResultDecoder;

public class ServerPlayersResultDecoder implements ResultDecoder<ServerPlayersResult> {

	public ServerPlayersResult decode(String string) {
		String[] stringSplit = string.split(" ");
		if(stringSplit[0].equals("ERROR")) {
			return new ServerPlayersResult(StatusCode.valueOf(stringSplit[1]));
		}
		Map<String, List<String>> playersByServers = new HashMap<String, List<String>>();
		for(String serverString : stringSplit) {
			String[] serverStringSplit = serverString.split("\\:");
			playersByServers.put(serverStringSplit[0], Arrays.asList(serverStringSplit[2].split("\\;")));
		}
		return new ServerPlayersResult(playersByServers);
	}

	public String getLabel() {
		return "SERVER_PLAYERS";
	}

}
