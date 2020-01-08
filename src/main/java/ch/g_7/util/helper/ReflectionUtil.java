package ch.g_7.util.helper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.stream.Collectors; 

public class ReflectionUtil {

	public static final Class<?>[] PRIMITIVE_WRAPPERS = {Boolean.class, Byte.class, Character.class, Double.class, Float.class, Integer.class, Long.class, Short.class, Void.class};
	
	public static Class<?> getWrapperOfPrimitive(Class<?> primitive){
		try {	
			for (Class<?> clazz : PRIMITIVE_WRAPPERS) {
				if(clazz.getField("TYPE").get(null).equals(primitive)) {
					return clazz;
				}
			}
			for (Class<?> clazz : PRIMITIVE_WRAPPERS) {
				if(clazz.getField("TYPE").get(null).equals(primitive)) {
					return clazz;
				}
			}
		}catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return primitive;
	}
	
	
	public static boolean isPrimitiveOrWrapper(Class<?> clazz) {
		return Arrays.asList(PRIMITIVE_WRAPPERS).contains(getWrapperOfPrimitive(clazz));
	}
	
	
	@SuppressWarnings("unchecked")
	public static <I> I implemment(Class<I> clazz, BiFunction<Method, Object[], Object> task) {
		return (I) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[] {clazz}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return task.apply(method, args);
			}
		});
	}
	



}
