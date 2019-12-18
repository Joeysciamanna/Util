package ch.g_7.util.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import ch.g_7.util.parse.SerializationParserUtil;

public class MethodCallWrapper<O> {

	private Method method;
	private List<Function<String, ?>> inputParsers;
	private Function<O, String> outputParser;
	private Object object;
	
	public MethodCallWrapper(Method method, Object object, Class<O> methodOutput) {
		inputParsers = new ArrayList<Function<String,?>>();
		for (Class<?> clazz : method.getParameterTypes()) {
			inputParsers.add(SerializationParserUtil.getFromStringParser(clazz));
		}
		outputParser = SerializationParserUtil.getToStringParser(methodOutput);
		this.object = object;
	}
	
	@SuppressWarnings("unchecked")
	public String call(String...args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<Object> params = new ArrayList<Object>();
		for (int i = 0; i < args.length; i++) {
			String string = args[i];
			params.add(inputParsers.get(i).apply(string));
		}
		O value = (O) method.invoke(object, params.toArray());
		return outputParser.apply(value);
	}

}
