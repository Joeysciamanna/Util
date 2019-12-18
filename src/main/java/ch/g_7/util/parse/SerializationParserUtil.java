package ch.g_7.util.parse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import ch.g_7.util.reflection.ClassUtil;
import ch.g_7.util.task.SecureRunner;

/**
 * Class used to generate parsers to pars all kind of Objects to String and back.
 * This Class also contains simple methods to serialize and deserialize Objects,
 * as well as the global Destringifyer.
 * 
 * @author Joey Sciamanna
 */
public class SerializationParserUtil {

	private static IDestringifyer destringifyer;
	private static Map<String, Method> WRAPPER_PARSER_METHODS = new HashMap<>();
	
	/**
	 * Get a Parser which is able to parse a Object of Class from into a String
	 * Either by using destringify methods or Serialization.
	 * If the given Object is primitive, primitive wrapper or a String toString is used.
	 * @param <I> The Type of the Object
	 * @param from The Class of the Object
	 * @return A parser able to parse Objects of the given Class to String
	 */
	public static <I> Function<I, String> getToStringParser(Class<I> from) {
		if (Stringifyable.class.isAssignableFrom(from)) {
			return (I i) -> ((Stringifyable) i).stringify();
		}
		if (ClassUtil.isPrimitiveOrWrapper(from) || String.class.isAssignableFrom(from)) {
			return (I i) -> Objects.toString(i);
		}
		return (I i) -> serialize(i);
	}


	/**
	 * Get a Parser which is able to parse a String to a Object of the given Class
	 * Either by using destringify methods, Serialization.
	 * If the given Object is primitive or primitive wrapper the valueOf method is used.
	 * If the given Object is of type String no changes will be applied.
	 * @param <O> The Type of the Object
	 * @param to The Class of the Object
	 * @return A parser able to parse A String into a Object of the given Class
	 */
	@SuppressWarnings("unchecked")
	public static <O> Function<String, O> getFromStringParser(Class<O> to) {
		if (Stringifyable.class.isAssignableFrom(to)) {
			for(Constructor<?> constructor : to.getConstructors()){
				if(constructor.getAnnotation(Destringifyable.class) != null && constructor.getParameterCount() == 1) {
					return new SecureRunner<>((String s) -> (O) constructor.newInstance(s));
				}
			}
			return (String s) -> (O) destringifyer.destringify(s);
		}
		if (String.class.isAssignableFrom(to)) {
			return (String s) -> (O) s;
		}
		if (ClassUtil.isPrimitiveOrWrapper(to)) {
			try {
				Method method;
				if(!WRAPPER_PARSER_METHODS.containsKey(to.getSimpleName())){
					method = to.getMethod("valueOf", String.class);
					method.setAccessible(true);
					WRAPPER_PARSER_METHODS.put(to.getSimpleName(), method);
				}else {
					 method = WRAPPER_PARSER_METHODS.get(to.getSimpleName());
				}
				return new SecureRunner<>((String s) -> (O) method.invoke(null, s));
			} catch (NoSuchMethodException | SecurityException e) {
				throw new RuntimeException(e);
			}
		}
		return (String s) -> (O) deserialize(s);
	}
	
	
	/**
	 * Simple serialization method
	 * @param o the Object to serialize
	 * @return the serialize Object as String
	 */
	public static String serialize(Object o) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(o);
			oos.close();
			return Base64.getEncoder().encodeToString(baos.toByteArray());
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Simple deserialization method
	 * @param string the String to be deserialized
	 * @return The deserialized String as Object
	 */
	public static Object deserialize(String string) {
		try {
			byte[] data = Base64.getDecoder().decode(string);
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
			Object o = ois.readObject();
			ois.close();
			return o;
		} catch(IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Set the global Destringifyer used to destringify all Stringifyable Objects,
	 * which don't have a Constructor with argument String and Destringifyable annotation
	 * @param destringifyer the global Destringifyer
	 */
	public static void setDestringifyer(IDestringifyer destringifyer) {
		SerializationParserUtil.destringifyer = destringifyer;
	}
}
