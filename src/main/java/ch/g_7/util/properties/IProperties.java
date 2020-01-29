package ch.g_7.util.properties;

import java.util.Optional;
import java.util.function.Supplier;

public interface IProperties {

//	String getFormated(PropKey<String> key, );
	
	String get(String key) throws IllegalArgumentException;
	
	<T> T get(PropKey<T> key) throws IllegalArgumentException;
	
	String get(String key, String defauld); //ret def if not found
	
	<T> T get(PropKey<T> key, T defauld); //ret def if not found
	
	String getOrPut(String key, String defauld);
	
	<T> T getOrPut(PropKey<T> key, T defauld);
		
	Optional<Prop> find(String key);
	
	
	void put(String key, String value);
	
	<T> void put(PropKey<T> key, T value); //?? realyy neaded
	
	void put(String key, Supplier<String> value);
	
	void put(Prop prop);
	
	
	boolean isEmpty();
	
	boolean contains(String key);
	
	boolean contains(PropKey<?> key);
}
