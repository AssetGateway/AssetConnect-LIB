package asset.connect.lib.request.impl;

import asset.connect.api.request.impl.ServerPlayersRequest;
import asset.connect.lib.request.RequestEncoder;
import asset.connect.lib.util.StringUtils;

public class ServerPlayersRequestEncoder implements RequestEncoder<ServerPlayersRequest> {

	public String encode(ServerPlayersRequest request) {
		return StringUtils.join(request.getServers(), ";");
	}

	public String getLabel() {
		return "SERVER_PLAYERS";
	}

	public Class<ServerPlayersRequest> getRequest() {
		return ServerPlayersRequest.class;
	}

}
