package ch.g_7.util.reflection;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ch.g_7.util.parse.SerializationParserUtil;
import ch.g_7.util.process.Task;

public class MethodArgParser {

	private List<ArgParser<?>> inputParsers;
	private Task<String, ?> outputParser;
	private boolean initialized;
	
	public void init(Method method) {
		inputParsers = new ArrayList<>();
		for(Class<?> clazz : method.getParameterTypes()) {
			inputParsers.add(new ArgParser<>(clazz));
		}
		outputParser = SerializationParserUtil.getFromStringParser(method.getReturnType());
		initialized = true;
	}
	

	public String[] parse(Object...args) {
		List<String> strings = new ArrayList<String>();
		for (int i = 0; i < args.length; i++) {
			strings.add(inputParsers.get(i).parse(args[i]));
		}
		return strings.toArray(new String[] {});
	}
	
	public Object parse(String string) {
		return outputParser.run(string);
	}
	
	public boolean isInitialized() {
		return initialized;
	}
	
	public static class ArgParser<I>{
		
		private Task<I, String> parser;
		
		public ArgParser(Class<I> argType) {
			parser = SerializationParserUtil.getToStringParser(argType);
		}
		
		@SuppressWarnings("unchecked")
		public String parse(Object object) {
			return parser.run((I) object);
		}
	}
}
