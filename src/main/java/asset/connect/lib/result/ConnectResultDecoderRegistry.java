package asset.connect.lib.result;

import asset.connect.lib.result.impl.AnnounceResultDecoder;
import asset.connect.lib.result.impl.AuthenticateResultDecoder;
import asset.connect.lib.result.impl.DirectResultDecoder;
import asset.connect.lib.result.impl.KeyResultDecoder;
import asset.connect.lib.result.impl.PlayerServerResultDecoder;
import asset.connect.lib.result.impl.RedirectResultDecoder;
import asset.connect.lib.result.impl.ServerPlayersResultDecoder;
import asset.connect.lib.result.impl.WhoamiResultDecoder;

public class ConnectResultDecoderRegistry extends ResultDecoderRegistry {

	public static final ConnectResultDecoderRegistry INSTANCE = new ConnectResultDecoderRegistry();
	
	private ConnectResultDecoderRegistry() {
		super.submit(new AnnounceResultDecoder());
		super.submit(new AuthenticateResultDecoder());
		super.submit(new DirectResultDecoder());
		super.submit(new KeyResultDecoder());
		super.submit(new PlayerServerResultDecoder());
		super.submit(new RedirectResultDecoder());
		super.submit(new ServerPlayersResultDecoder());
		super.submit(new WhoamiResultDecoder());
	}
	
}
