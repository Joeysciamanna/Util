package ch.g_7.util.reflection;

import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ch.g_7.util.task.Task;

public class InterfaceInvokeListner<T> {

	private T interfase;
	private Map<String, Entry<MethodArgParser, Task<?,?>>> methodCallHandlers;
	private Method lastCalled;
	
	public InterfaceInvokeListner(Class<T> clazz) {
		methodCallHandlers = new HashMap<String, Entry<MethodArgParser, Task<?,?>>>();
		interfase = ClassUtil.implemment(clazz, (e) -> onCall(e.getKey(), e.getValue()));
	}
	
	@SuppressWarnings("unchecked")
	private Object onCall(Method method, Object...args) {
		String methodName = ClassUtil.createUniqueMethodString(method);
		if(methodCallHandlers.containsKey(methodName)) {
			Entry<MethodArgParser, Task<?,?>> entry = methodCallHandlers.get(methodName);
			MethodArgParser parser = entry.getKey();
			Task<?,?> callListner = entry.getValue();
			if(parser == null) {
				return ((Task<Object[], Object>)callListner).run(args);
			}else if(!parser.isInitialized()){
				parser.init(method);
			}
			return parser.parse(((Task<String[],String>)callListner).run(parser.parse(args)));
		}else {
			lastCalled = method;
			return null;
		}
	}
	
	
	public void addStringifyabledCallListner(Task<String[], String> callListner) {
		methodCallHandlers.put(ClassUtil.createUniqueMethodString(lastCalled), new AbstractMap.SimpleEntry<>(new MethodArgParser(), callListner));
	}
	
	public void addCallListner(Task<Object[], Object> callListner) {
		methodCallHandlers.put(ClassUtil.createUniqueMethodString(lastCalled), new AbstractMap.SimpleEntry<>(null, callListner));
	}
	
	
	
	public T getInterface() {
		return interfase;
	}
	
	
	
	
}
