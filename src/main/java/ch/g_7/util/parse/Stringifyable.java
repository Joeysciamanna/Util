package ch.g_7.util.parse;

/**
 * Interface used to stringify Objects.
 * 
 * For Destringification add a constructor with 1 String argument and {@link Destringifiable} annotation.
 * Or create a global {@link IDestringifier}
 * 
 * @author Joey Sciamanna
 */
public interface Stringifyable {

	/**
	 * This method should stringify this Object by creating a string containing
	 * all required data to recreate this Object by the Destringifyer.
	 * @return a String containing important information of this Object.
	 */
    String stringify();

}
