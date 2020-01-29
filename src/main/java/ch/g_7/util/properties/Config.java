package ch.g_7.util.properties;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Config contains application configurations, witch the user can't change.
 * 
 * @author Joey Sciamanna
 */
public class Config implements IProperties {

	private Set<Prop> properties;
	
	public Config() {
		this.properties = new HashSet<Prop>();
	}

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
	

}















