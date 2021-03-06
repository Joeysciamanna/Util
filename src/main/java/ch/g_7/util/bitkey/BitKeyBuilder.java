package ch.g_7.util.bitkey;

/**
 * Simple helper class for producing GitKeys
 * 
 * @author Joey Sciamanna
 */
public class BitKeyBuilder {
	
	private int key = 0;
	
	public BitKey next() {
		key = key == 0 ? 1 : (key * 2);
		return get();
	}
	
	
	public BitKey get() {
		return new BitKey(key);
	}

	public int getInt() {
		return key;
	}
}
