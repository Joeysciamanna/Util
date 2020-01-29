package ch.g_7.util.properties;

import java.util.Objects;
import java.util.function.Supplier;

public class Prop {
	
	private String key;
	private Supplier<String> value;
	private String comment;
	
	public Prop(String key, String value, String comment) {
		this(key, ()->value, comment);
	}
	
	public Prop(String key, Supplier<String> value, String comment) {
		this.key = key;
		this.value = value;
		this.comment = comment;
	}

	public String getValue() {
		return value.get();
	}
	
	public void setValue(Supplier<String> value) {
		this.value = value;
	}
	
	public void setValue(String value) {
		this.value = ()->value;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public boolean equals(Prop prop) {
		return getKey().equals(prop.getKey()) && getValue().equals(prop.getValue());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getKey(), getValue());
	}
}