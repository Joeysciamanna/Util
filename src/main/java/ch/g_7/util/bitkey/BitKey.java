package ch.g_7.util.bitkey;

import ch.g_7.util.common.IIdentifier;

/**
 * A Key that can be multiple values in the same time.
 * 
 * @author Joey Sciamanna
 */
public class BitKey implements IIdentifier {

	private final int value;


	public BitKey(int value) {
		this.value = value;
	}


	public BitKey merge(BitKey bitKey) {
		return new BitKey(value | bitKey.getValue());
	}
	
	public BitKey merge(int bitKey) {
		return new BitKey(value | bitKey);
	}

	public BitKey remove(BitKey bitKey) {
		return new BitKey(value & (~bitKey.getValue()));
	}

	public BitKey remove(int bitKey) {
		return new BitKey(value & (~bitKey));
	}

	public boolean contains(BitKey bitKey) {
		return (value & bitKey.getValue()) != 0;
	}
	
	public boolean contains(int bitKey) {
		return (value & bitKey) != 0;
	}

	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof BitKey && ((BitKey) obj).value == value;
	}
	
	@Override
	public int hashCode() {
		return value;
	}
	
	@Override
	public String toString() {
		return Integer.toBinaryString(value);
	}
	
	public int getValue() {
		return value;
	}


}
