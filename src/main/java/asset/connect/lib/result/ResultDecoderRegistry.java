package asset.connect.lib.result;

import java.util.HashMap;
import java.util.Map;

public class ResultDecoderRegistry {

	private Map<String, ResultDecoder<?>> registryByLabel = new HashMap<String, ResultDecoder<?>>();
	
	public void submit(ResultDecoder<?> resultDecoder) {
		if(resultDecoder == null) {
			return;
		}
		this.registryByLabel.put(resultDecoder.getLabel(), resultDecoder);
	}
	
	public ResultDecoder<?> getByLabel(String label) {
		return this.registryByLabel.get(label.toUpperCase());
	}
	
}
