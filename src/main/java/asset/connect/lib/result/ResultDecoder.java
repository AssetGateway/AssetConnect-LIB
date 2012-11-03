package asset.connect.lib.result;

import asset.connect.api.result.Result;

public interface ResultDecoder<T extends Result> {

	public T decode(String string);

	public String getLabel();
	
}
