package ch.g_7.util.common;

/**
 * An Identifier with should only be implemented by an Enum.
 * 
 * @author Joey Sciamanna
 * @param <E> Itself
 */
public interface IIdentifier<E extends Enum<E> & IIdentifier<E>> {

	int compareTo(E o);

	Class<E> getDeclaringClass();
	
	String name();
	
	int ordinal();
}
