package ch.g_7.util.stuff;

import java.util.ArrayList;

import ch.g_7.util.process.Task;

public class SecureRunner<I,O> implements Task<I, O>{

	private Exception cause;
	private boolean success = false;
	private boolean throwRunntime = true;
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
	
	public SecureRunner<I,O> open(Passable... passables) {
		for (Passable passable : passables) {
			openables.add(passable);
			closeables.add(passable);	
		}
		return this;
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
	
	
	public O run() {
		return run(null);
	}
	
	@Override
	public O run(I input) {
		O result = null;
		try {
			for (Openable openable : openables) {
				if(openable != null) {
					openable.open();	
				}
			}
			result = throwz.run(input);
			success = true;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			fail(e);
		} finally {
			try {
				for (AutoCloseable closeable : closeables) {
					if(closeable != null) {
						closeable.close();	
					}
				}
			} catch (RuntimeException e) {
				throw e;
			} catch (Exception e) {
				fail(e);
			}
		}
		return result;
	}
	
	private void fail(Exception e) {
		cause = e;
		success = false;
		if(throwRunntime) {
			exception.initCause(e);
			throw exception;
		}
	}

	public SecureRunner<I,O> throwRunntime(boolean throwRunntime) {
		this.throwRunntime = throwRunntime;
		return this;
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
