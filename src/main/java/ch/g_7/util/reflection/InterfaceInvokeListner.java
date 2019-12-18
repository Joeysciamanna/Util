package ch.g_7.util.reflection;

import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

public class InterfaceInvokeListner<T> {

	private T interfase;
	private Map<String, Entry<MethodArgParser, Function<?,?>>> methodCallHandlers;
	private Method lastCalled;
	
	public InterfaceInvokeListner(Class<T> clazz) {
		methodCallHandlers = new HashMap<String, Entry<MethodArgParser, Function<?,?>>>();
		interfase = ClassUtil.implemment(clazz, (m, args) -> onCall(m, args));
	}
	
	@SuppressWarnings("unchecked")
	private Object onCall(Method method, Object...args) {
		String methodName = ClassUtil.createUniqueMethodString(method);
		if(methodCallHandlers.containsKey(methodName)) {
			Entry<MethodArgParser, Function<?,?>> entry = methodCallHandlers.get(methodName);
			MethodArgParser parser = entry.getKey();
			Function<?,?> callListner = entry.getValue();
			if(parser == null) {
				return ((Function<Object[], Object>)callListner).apply(args);
			}else if(!parser.isInitialized()){
				parser.init(method);
			}
			return parser.parse(((Function<String[],String>)callListner).apply(parser.parse(args)));
		}else {
			lastCalled = method;
			return null;
		}
	}
	
	
	public void addStringifyabledCallListner(Function<String[], String> callListner) {
		methodCallHandlers.put(ClassUtil.createUniqueMethodString(lastCalled), new AbstractMap.SimpleEntry<>(new MethodArgParser(), callListner));
	}
	
	public void addCallListner(Function<Object[], Object> callListner) {
		methodCallHandlers.put(ClassUtil.createUniqueMethodString(lastCalled), new AbstractMap.SimpleEntry<>(null, callListner));
	}
	
	
	
	public T getInterface() {
		return interfase;
	}
	
	
	
	
}
