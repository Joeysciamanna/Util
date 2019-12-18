package ch.g_7.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import ch.g_7.util.stuff.Initializable;

public class ObjectCallWrapper implements Initializable{

	private Object synchronizable;
	private HashMap<String, MethodCallWrapper<?>> methods;
	private Class<? extends Annotation> annotation;
	
	public ObjectCallWrapper(Object synchronizable, Class<? extends Annotation> requiredAnnotation) {
		this.synchronizable = synchronizable;
		this.annotation = requiredAnnotation;
	}
	
	public ObjectCallWrapper(Object synchronizable) {
		this(synchronizable, null);
	}

	@Override
	public void init() {
		for(Method method : synchronizable.getClass().getMethods()) {
			if(annotation == null || method.isAnnotationPresent(annotation)) {
				methods.put(ClassUtil.createUniqueMethodString(method), new MethodCallWrapper<>(method, synchronizable, method.getReturnType()));
			}
		}
	}
	

	public String call(String name, String...args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return methods.get(name).call(args);
	}

}
