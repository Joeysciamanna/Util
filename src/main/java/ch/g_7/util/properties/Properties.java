package ch.g_7.util.properties;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class Properties implements IProperties {

	private List<Prop> properties;
	
	@Override
	public String get(String key) throws IllegalArgumentException {
		return find(key).orElseThrow().getValue();
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
			put(key, defauld);
			return defauld;
		}
		return get(key);
	}

	@Override
	public <T> T getOrPut(PropKey<T> key, T defauld) {
		if(!contains(key)) {
			put
		}
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public Optional<Prop> find(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public void put(String key, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void put(String key, Supplier<String> value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void put(Prop prop) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(PropKey<?> key) {
		return contains(key.name);
	}
	
}
