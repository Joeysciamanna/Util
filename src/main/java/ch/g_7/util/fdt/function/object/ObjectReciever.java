package ch.g_7.util.fdt.function.object;

import ch.g_7.util.fdt.data.Metadata;
import ch.g_7.util.fdt.data.Request;
import ch.g_7.util.fdt.exception.FDTException;
import ch.g_7.util.fdt.function.Reciever;
import ch.g_7.util.parse.SerializationParserUtil;
import ch.g_7.util.process.Task;

public abstract class ObjectReciever<T> extends Reciever {

	private Task<String, T> parser;
	private String className;
	
	public ObjectReciever(Class<T> clazz) {
		parser = SerializationParserUtil.getFromStringParser(clazz);
		className = clazz.getSimpleName();
	}
	
	@Override
	public String recieve(Request request) throws FDTException {
		return recieveObject(parser.run(request.getData()), request.getMetadata());
	}
	
	public abstract String recieveObject(Object object, Metadata metadata);
	
	@Override
	public String getName() {
		return "object-"+className;
	}

}
