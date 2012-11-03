package asset.connect.lib.request.impl;

import asset.connect.api.request.impl.PlayerServerRequest;
import asset.connect.lib.request.RequestEncoder;

public class PlayerServerRequestEncoder implements RequestEncoder<PlayerServerRequest> {

	public String encode(PlayerServerRequest request) {
		return request.getPlayer();
	}

	public String getLabel() {
		return "PLAYER_SERVER";
	}

	public Class<PlayerServerRequest> getRequest() {
		return PlayerServerRequest.class;
	}

}
