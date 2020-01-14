package ch.g_7.util.task;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Supplier;

import ch.g_7.util.common.Openable;

public class SecureRunner<I,O> implements Supplier<O>, Function<I, O> {

	private Exception cause;
	private boolean success = false;
	private RuntimeException exception;
	
	private ThrowingTask<I, O> throwz;
	
	private ArrayList<Openable> openables;
	private ArrayList<AutoCloseable> closeables;
	
	
	public SecureRunner(ThrowingTask<I, O> throwz) {
		openables = new ArrayList<>();
		closeables = new ArrayList<>();
		this.throwz = throwz;
		exception = new RuntimeException();
	}
	
	public SecureRunner(ThrowingVoidTask throwz) {
		this((o) -> {throwz.run(); return null; });
	}
	
	public SecureRunner(ThrowingEmptyTask<O> throwz) {
		this((o) -> throwz.run());
	}

	
	public SecureRunner<I,O> open(Openable...openables) {
		for (Openable openable : openables) {
			this.openables.add(openable);
		}
		return this;
	}
	
	public SecureRunner<I,O> close(AutoCloseable...closeables) {
		for (AutoCloseable closeable : closeables) {
			this.closeables.add(closeable);
		}
		return this;
	}
	
	
	public O get() {
		return apply(null);
	}
	
	public O apply(I input) {
		O result = null;
		try {
			for (Openable openable : openables) {
				if(openable != null) {
					openable.open();	
				}
			}
			result = throwz.run(input);
			success = true;
		} catch (Exception e) {
			fail(e);
		} finally {
			try {
				for (AutoCloseable closeable : closeables) {
					if(closeable != null) {
						closeable.close();	
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return result;
	}
	
	private void fail(Exception e) {
		cause = e;
		success = false;
		if(exception != null) {
			exception.initCause(e);
			throw exception;
		}
	}
	
	public SecureRunner<I, O> throwException(RuntimeException exception){
		this.exception = exception;
		return this;
	}
	
	public Exception getCause() {
		return cause;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	@FunctionalInterface
	public static interface ThrowingTask<I,O> {
		public O run(I i) throws Exception;
	}
	
	@FunctionalInterface
	public static interface ThrowingVoidTask {
		public void run() throws Exception;
	}
	
	@FunctionalInterface
	public static interface ThrowingEmptyTask<O> {
		public O run() throws Exception;
	}
	

}
