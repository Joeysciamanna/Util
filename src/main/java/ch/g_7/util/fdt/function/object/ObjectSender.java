package ch.g_7.util.fdt.function.object;

import ch.g_7.util.fdt.FDTConnection;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.exception.ServerException;
import ch.g_7.util.fdt.function.Sender;
import ch.g_7.util.parse.SerializationParserUtil;
import ch.g_7.util.process.Task;

public abstract class ObjectSender<T> extends Sender {

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
		return "object" + className;
	}

	public final Response sendObject(T obj) throws ServerException {
		return send(parser.run(obj));
	}
}
