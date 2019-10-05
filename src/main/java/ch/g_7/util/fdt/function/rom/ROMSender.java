package ch.g_7.util.fdt.function.rom;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import ch.g_7.util.fdt.FDTConnection;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.function.Sender;
import ch.g_7.util.reflection.ClassUtil;
import ch.g_7.util.reflection.MethodArgParser;

public class ROMSender extends Sender{

	Map<Long, Map<String,MethodArgParser>> argParser;
	
	public ROMSender(FDTConnection connection, String endpoint) {
		super(connection, endpoint);
		argParser = new HashMap<Long, Map<String,MethodArgParser>>();
	}
	
	public ROMSender(FDTConnection connection) {
		super(connection);
		argParser = new HashMap<Long, Map<String,MethodArgParser>>();
	}
	
	public final Response sendROM(long objId, String methodName, Object...args) {
		ROMData romData = new ROMData(objId, methodName, Arrays.asList(argParser.get(objId).get(methodName).parse(args)));
		return send(romData.stringify());
	}

	
	public <I> I add(Class<I> interfaseClass, long objId) {
		Map<String, MethodArgParser> argParsers = new HashMap<String, MethodArgParser>();
		I interfase = ClassUtil.implemment(interfaseClass, (e)->sendROM(objId, ClassUtil.createUniqueMethodString(e.getKey()), e.getValue()));
		
		for(Method method : interfaseClass.getMethods()) {
			MethodArgParser argParser = new MethodArgParser();
			argParser.init(method);
			argParsers.put(ClassUtil.createUniqueMethodString(method), argParser);
		}
		
		return interfase;
	}
	
	
	@Override
	protected String getName() {
		return "rom";
	}


}
