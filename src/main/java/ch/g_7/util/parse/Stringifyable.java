package ch.g_7.util.parse;

/**
 * Interface used to stringify Objects.
 * 
 * @author Joey Sciamanna
 */
public interface Stringifyable {

	/**
	 * This method should stringify this Object by creating a string containing
	 * all required data to recreate this Object by the Destringifyer.
	 * @return a String containing important information of this Object.
	 */
    public String stringify();

}
