package ch.g_7.util.common;

import java.util.function.Supplier;

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
		if(instance != null) {
			return instance.getClass().equals(clazz);
		}
		// Can't equal if never created
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
