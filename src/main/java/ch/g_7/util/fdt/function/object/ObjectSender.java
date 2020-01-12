package ch.g_7.util.fdt.function.object;

import java.util.function.Consumer;

import ch.g_7.util.fdt.FDTConnection;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.exception.ServerException;
import ch.g_7.util.fdt.function.Sender;
import ch.g_7.util.parse.SerializationParserUtil;
import ch.g_7.util.task.Task;

/**
 * Sender to send objects of type T
 * @param <T> The type of the object
 * 
 * @author Joey Sciamanna
 */
public class ObjectSender<T> extends Sender {

	private Task<T, String> parser;
	private String className;
	
	public ObjectSender(FDTConnection connection, String endpoint, Class<T> clazz) {
		super(connection, endpoint);
		parser = SerializationParserUtil.getToStringParser(clazz);
		className = clazz.getSimpleName();
	}
	
	public ObjectSender(FDTConnection connection, Class<T> clazz) {
		super(connection);
		parser = SerializationParserUtil.getToStringParser(clazz);
		className = clazz.getSimpleName();
	}
	
	@Override
	public String getName() {
		return "object-" + className;
	}

	/**
	 * Parses and sends the given object
	 * @param obj The object to send
	 * @return The response from the server
	 * @throws ServerException Thrown when a error occurs at the server 
	 */
	public final Response sendObject(T obj) throws ServerException {
		return send(parser.run(obj));
	}
	
	/**
	 * Parses and sends the given object
	 * @param obj The object to send
	 * @return The response from the server
	 * @throws ServerException Thrown when a error occurs at the server 
	 */
	public final void sendObjectAsync(T obj,Consumer<Response> consumer) {
		sendAsync(parser.run(obj), consumer);
	}
}
