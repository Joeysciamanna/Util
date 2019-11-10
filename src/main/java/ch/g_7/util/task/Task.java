package ch.g_7.util.task;

import java.util.concurrent.CompletableFuture;

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
	
	
	@FunctionalInterface
	public static interface VoidTask<I> extends Task<I, Void>{
		
		@Override
		default Void run(I i) {
			runVoid(i);
			return null;
		}
		
		public void runVoid(I i);
	}
	
	
	@FunctionalInterface
	public static interface EmptyTask<O> extends Task<Void, O>{
		
		@Override
		default O run(Void i) {
			return runEmpty();
		}
		
		public O runEmpty();
	}
	
	
	@FunctionalInterface
	public static interface SimpleTask extends Task<Void, Void>, EmptyTask<Void>, VoidTask<Void>{
		
		@Override
		default void runVoid(Void i) {
			runSimple();
		}
		
		@Override
		default Void runEmpty() {
			runSimple();
			return null;
		}
		
		@Override
		default Void run(Void i) {
			runSimple();
			return null;
		}
		
		public void runSimple();
	}
	
	
	@FunctionalInterface
	public static interface AsyncTask<I> extends VoidTask<I>{
		
		@Override
		default void runVoid(I i) {
			CompletableFuture.runAsync(() -> {
			   runAsync(i);
			});
		}
		
		public void runAsync(I i);
	}
}


