package ch.g_7.util.bitkey;

public class BitKey {

	private final int value;
	
	public BitKey(int value) {
		this.value = value;
	}
	
	public BitKey merge(BitKey bitKey) {
		return merge(bitKey.getValue());
	}
	
	public BitKey merge(int bitKey) {
		return new BitKey(merge(value, bitKey));
	}
	
	public static int merge(int bitKey1, int bitKey2) {
		return bitKey1 | bitKey2;
	}
	
	public boolean contains(BitKey bitKey) {
		return contains(bitKey.getValue());
	}
	
	public boolean contains(int bitKey) {
		return contains(value, bitKey);
	}
	
	public static boolean contains(int bitKey1, int bitKey2) {
		return (bitKey1 & bitKey2) != 0;
	}
	
	public int getValue() {
		return value;
	}
}
