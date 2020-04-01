package ch.g_7.util.properties;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

import ch.g_7.util.parse.Destringifyable;
import ch.g_7.util.parse.Stringifyable;

public class Properties implements IProperties, Stringifyable {

	private Set<Prop> properties;
	
	public Properties(Set<Prop> properties) {
		this.properties = properties;
	}
	
	public Properties() {
		this(new HashSet<Prop>());
	}
	
	@Deprecated
	@Destringifyable
	public Properties(String txt) {
		this(PropertyParser.fromString(txt));
	}
	
	@Override
	public String stringify() {
		return PropertyParser.toString(properties);
	}
	
	@Override
	public String get(String key) throws IllegalArgumentException {
		return find(key)
				.orElseThrow(()-> new IllegalArgumentException("No value for key ["+key+"] found"))
				.getValue();
	}

	@Override
	public <T> T get(PropKey<T> key) throws IllegalArgumentException {
		return key.cast(get(key.name));
	}

	@Override
	public String get(String key, String defauld) {
		return find(key).map((p)->p.getValue()).orElseGet(()->defauld);
	}

	@Override
	public <T> T get(PropKey<T> key, T defauld) {
		return find(key.name).map((p)->key.cast(p.getValue())).orElseGet(()->defauld);
	}

	@Override
	public String getOrPut(String key, String defauld) {
		if(!contains(key)) {
			put(new Prop(key, defauld, null));
			return defauld;
		}
		return get(key);
	}

	@Override
	public <T> T getOrPut(PropKey<T> key, T defauld) {
		return key.cast(getOrPut(key.name, String.valueOf(defauld)));
	}

	@Override
	public Optional<Prop> find(String key) {
		for (Prop prop : properties) {
			if(prop.getKey().equals(key)) {
				return Optional.of(prop);
			}
		}
		return Optional.empty();
	}

	@Override
	public void set(String key, String value) {
		set(key, ()->value);
	}

	@Override
	public void set(String key, Supplier<String> value) {
		set(new Prop(key, value, null));
	}

	@Override
	public void set(Prop prop) {
		if(contains(prop.getKey())) {
			Prop setable = find(prop.getKey()).get();
			setable.setValue(prop.getValue());
			if(prop.getComment() != null) {
				setable.setComment(prop.getComment());
			}
		}else {
			properties.add(prop);
		}
		
	}
	
	@Override
	public void put(Prop prop) throws IllegalArgumentException {
		if(contains(prop.getKey())) {
			throw new IllegalArgumentException("Property ["+prop.getKey()+"] alredy set");
		}
		properties.add(prop);
	}

	@Override
	public void remove(PropKey<?> key) throws IllegalArgumentException {
		remove(key.name);
	}
	
	@Override
	public void remove(String key) throws IllegalArgumentException {
		if(!contains(key)) {
			throw new IllegalArgumentException("Property ["+key+"] not set");
		}
		properties.removeIf((p)->p.getKey().equals(key));
	}
	
	@Override
	public boolean contains(String key) {
		for (Prop prop : properties) {
			if(prop.getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean contains(PropKey<?> key) {
		return contains(key.name);
	}
	
	@Override
	public boolean isSet(Prop prop) {
		return isSet(prop.getKey(), prop.getValue());
	}
	
	@Override
	public boolean isSet(PropKey<?> key, String value) {
		return isSet(key.name, value);
	}
	
	@Override
	public boolean isSet(String key, String value) {
		for (Prop prop : properties) {
			if(prop.getKey().equals(key) && prop.getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void fill(Set<Prop> properties){
		this.properties.addAll(properties);
	}

	@Override
	public Set<Prop> getAllProperties() {
		return new HashSet<>(properties);
	}

	@Override
	public boolean isEmpty() {
		return properties.isEmpty();
	}

}
