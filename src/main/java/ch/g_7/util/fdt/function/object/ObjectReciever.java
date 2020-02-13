package ch.g_7.util.fdt.function.object;

import java.util.function.Function;

import ch.g_7.util.fdt.data.Metadata;
import ch.g_7.util.fdt.data.Request;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.exception.FDTException;
import ch.g_7.util.fdt.function.Receiver;
import ch.g_7.util.parse.ParserUtil;

/**
 * Receiver to handle object requests with objects of type T 
 * @param <T> The type of the object
 * 
 * @author Joey Sciamanna
 */
public abstract class ObjectReciever<T> extends Receiver {

	private Function<String, T> parser;
	private String className;

	
	public ObjectReciever(Class<T> clazz) {
		parser = ParserUtil.getFromStringParser(clazz);
		className = clazz.getSimpleName();
	}
	
	/**
	 * Handles incoming requests and invokes receiveObject with the received object
	 */
	@Override
	public Response recieve(Request request) throws FDTException {
		return recieveObject(parser.apply(request.getData()), request.getMetadata());
	}
	
	/**
	 * Handles the received and parsed object
	 * @param object The received object
	 * @param metadata The received metadata
	 * @return The response
	 */
	public abstract Response recieveObject(T object, Metadata metadata);
	
	@Override
	public String getName() {
		return "object-"+className;
	}

}
