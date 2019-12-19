package ch.g_7.util.helper;

public class BiValue<K,V> {

	private K key;
	private V value;
	
	public BiValue(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public BiValue() {}
	
	public void setKey(K key) {
		this.key = key;
	}
	
	public void setValue(V value) {
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
}
