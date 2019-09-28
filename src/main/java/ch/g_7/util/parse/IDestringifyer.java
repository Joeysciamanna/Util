package ch.g_7.util.parse;

/**
 * The Destringifyer is used to destringify all given Strings into Object
 * for better control a tree structure is recommended to organize multiple
 * smaller Destringifyer
 * 
 * @author Joey Sciamanna
 */
public interface IDestringifyer {

	/**
	 * Destringify the given Object
	 * This Method is used to destringify a destrigified Object.
	 * @param string a String defining an object in its state
	 * @return a Object matching the data Strings state
	 */
	public abstract Object destringify(String string);
}
