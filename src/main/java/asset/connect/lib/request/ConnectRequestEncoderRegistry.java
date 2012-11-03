package asset.connect.lib.request;

import asset.connect.lib.request.impl.AnnounceRequestEncoder;
import asset.connect.lib.request.impl.AuthenticateRequestEncoder;
import asset.connect.lib.request.impl.DirectRequestEncoder;
import asset.connect.lib.request.impl.KeyRequestEncoder;
import asset.connect.lib.request.impl.PlayerServerRequestEncoder;
import asset.connect.lib.request.impl.RedirectRequestEncoder;
import asset.connect.lib.request.impl.ServerPlayersRequestEncoder;
import asset.connect.lib.request.impl.WhoamiRequestEncoder;

public class ConnectRequestEncoderRegistry extends RequestEncoderRegistry {

	public static final ConnectRequestEncoderRegistry INSTANCE = new ConnectRequestEncoderRegistry();
	
	private ConnectRequestEncoderRegistry() {
		super.submit(new AnnounceRequestEncoder());
		super.submit(new AuthenticateRequestEncoder());
		super.submit(new DirectRequestEncoder());
		super.submit(new KeyRequestEncoder());
		super.submit(new PlayerServerRequestEncoder());
		super.submit(new RedirectRequestEncoder());
		super.submit(new ServerPlayersRequestEncoder());
		super.submit(new WhoamiRequestEncoder());
	}
	
}
