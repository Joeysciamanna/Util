package ch.g_7.util.process;

/**
 * Simple Task
 *
 * @param <I> The input Type
 * @param <O> The output Type
 *
 * @author Joey Sciamanna
 */
@FunctionalInterface
public interface Task<I,O> {

	/**
	 * Run this Task
	 * @param i the input
	 * @return the output
	 */
	public O run(I i);
}
