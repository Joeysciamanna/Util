package ch.g_7.util.properties;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

import ch.g_7.util.helper.Formator;

/**
 * Settings contains setting commonly read from a .prop file, changable by the user.
 * 
 * @author Joey Sciamanna
 */
public final class Settings implements IProperties{

	private Set<Prop> properties;
	
	public Settings(Set<Prop> properties) {
		this.properties = properties;
	}


//	public void write(OutputStream outputStream) throws IOException {
//		outputStream.write(parse(properties).getBytes());
//		outputStream.close();
//	}
	
//	public String getFormated(PropKey<String> key) {
//		return Formator.format(get(key));
//	}
	
	@Override
	public <T> T get(PropKey<T> key) throws IllegalArgumentException {
		return key.cast(get(key.name));
	}
	
	@Override
	public String get(String key) throws IllegalArgumentException {
		Prop prop  = find(key);
		if(prop == null) {
			throw new IllegalArgumentException("No value for key ["+key+"] found");
		}
		return prop.getValue();
	}

	@Override
	public <T> T get(PropKey<T> key, String defauld) {
		Prop prop = find(key.name);
		return key.cast(prop == null ? defauld : prop.getValue());
	}
	
	@Override
	public Prop find(String key) {
		for (Prop prop : properties) {
			if(prop.getKey().equals(key)) {
				return prop;
			}
		}
		return null;
	}
	
	/**
	 * Sets an App Property
	 * @param key
	 * @param value
	 */
	public void set(String key, Supplier<String> value) {
		appProperties.add(new Prop(key, value, ""));
	}
	
	/**
	 * Sets an App Property
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		appProperties.add(new Prop(key, value, ""));
	}
	
	/**
	 * Adds an Property to the Properties (File)
	 * @param key
	 * @param value
	 */
	public Prop put(String key, String value) {
		return put(key, value, null);
	}

	/**
	 * Adds an Property to the Properties (File)
	 * @param key
	 * @param value
	 * @param comment
	 * @return
	 */
	public Prop put(String key, String value, String comment) {
		Prop prop = new Prop(key, value, comment); 
		properties.add(prop); 
		return prop;
	}
	
	public boolean isEmpty() {
		return properties.isEmpty();
	}
	
	public String getVersion() {
		return get("VERSION", "-1").getValue();
	}
	
	public boolean contains(String key) {
		return properties.stream().anyMatch((p)->p.getKey().equals(key)) || appProperties.stream().anyMatch((p)->p.getKey().equals(key));
	}

	

}
