package ch.g_7.util.fdt.function.string;

import ch.g_7.util.fdt.data.Metadata;
import ch.g_7.util.fdt.data.Request;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.exception.FDTException;
import ch.g_7.util.fdt.function.Reciever;

public abstract class StringReciever extends Reciever{

	
	@Override
	public final Response recieve(Request request) throws FDTException {
		return recieveString(request.getData(), request.getMetadata());
	}
	
	public abstract Response recieveString(String data, Metadata metadata) throws FDTException;
	

	@Override
	public String getName() {
		return "string";
	}


}
