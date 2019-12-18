package ch.g_7.util.helper;

import java.util.function.Function;

public class PropKey<T> {

	public static PropKey<String> getStringKey(String name) {
		return new PropKey<>(name, (s) -> s);
	}

	public static PropKey<Integer> getIntKey(String name) {
		return new PropKey<>(name, Integer::valueOf);
	}

	public static PropKey<Float> getFloatKey(String name) {
		return new PropKey<>(name, Float::valueOf);
	}

	public static <T> PropKey<T> getCustom(String name, Function<String, T> caster) {
		return new PropKey<>(name, caster);
	}
	
	public final String name;
	private final Function<String, T> caster;

	protected PropKey(String name, Function<String, T> caster) {
		this.name = name;
		this.caster = caster;
	}

	public T cast(String s) {
		return caster.apply(s);
	}
	
	
}
