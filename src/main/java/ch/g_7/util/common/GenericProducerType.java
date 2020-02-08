package ch.g_7.util.common;

import java.util.function.Supplier;

/**
 * A Factory like Producer producing the suppliers value.
 * Also has some helper methods like typeEquals, cast and used.
 * 
 * @author Joey Sciamanna
 * 
 * @param <T> The type witch will be produced
 */
public class GenericProducerType<T> {

	private Supplier<T> supplier;
	private T instance;
	
	public GenericProducerType(Supplier<T> supplier) {
		this.supplier = supplier;
	}
	
	public T get() {
		return instance = supplier.get();	
	}
	
	public boolean used() {
		return instance != null;
	}
	
	public boolean typeEquals(Class<?> clazz) {
		if(used()) {
			return instance.getClass().equals(clazz);
		}
		// Can't equal if never used ( created
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public T cast(Object type){
		if(typeEquals(type.getClass())) {
			return (T) type;
		}
		throw new IllegalArgumentException("Type cant be casted, type doesnt match");
	}
}
