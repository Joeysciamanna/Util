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
	
	
	void set(String key, String value);
	
	void set(String key, Supplier<String> value);
	
	void set(Prop prop);
	
	void put(Prop prop) throws IllegalArgumentException;
	
	void remove(PropKey<?> key) throws IllegalArgumentException;
	
	void remove(String key) throws IllegalArgumentException;
	
	boolean isEmpty();
	
	boolean contains(String key);
	
	boolean contains(PropKey<?> key);
	
	boolean isSet(String key, String value);
	
	boolean isSet(PropKey<?> key, String value);
	
	boolean isSet(Prop prop);
}
