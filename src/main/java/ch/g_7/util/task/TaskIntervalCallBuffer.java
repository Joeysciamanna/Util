package ch.g_7.util.task;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * This Class can be used to call a task all x milliseconds,
 * with on optional callBuffer which allows the Task to be called multiple Times a run
 * instead of once every interval. This can result in better Performance, because it only
 * need to be called every interval * callBuffer instead of every interval.
 * The Task is not called automatically, this Task must be called to try execute the wrapped
 * Task, if the interval is then reached, the Task will be called.
 * If the Wrapped Task missed calls, then the Task will be called for every missed call.
 *
 * @param <T> the Input type of this Task
 *
 * @author Joey Sciamanna
 */
public class TaskIntervalCallBuffer<T>  {

	private Consumer<T> task;
	
	private long intervall;
	private long callBuffer;
	private long lastCall;
	private boolean enabled;
	
	public TaskIntervalCallBuffer(Consumer<T> task, long intervall, long callBuffer) {
		this.task = task;
		this.intervall = intervall;
		enabled = true;
		resetLastCall();
	}
	
	public TaskIntervalCallBuffer(Consumer<T> task, long intervall) {
		this(task, intervall, 0);
	}
	
	/**
	 * Tries running the wrapped Task,
	 * if the interval is reached the Task will be called for every missed call.
	 * if the interval is not reached, the task wont be called.
	 */
	public synchronized void run(T t) {
		if(enabled) {
			long actTime = System.currentTimeMillis();
			long div = actTime - lastCall;
			long missedCalls;
			if((missedCalls = div/intervall)>0) {
				for(int i = 0; i < (missedCalls+callBuffer); i++) {
					task.accept(t);
				}
				lastCall = actTime - (div%intervall) + (callBuffer*intervall);
			}
		} else {
			resetLastCall();
		}
	}

	public synchronized void runAsync(T t) {
		CompletableFuture.runAsync(() -> {
			run(t);
		});
	}
	
	/**
	 * @return the intervall
	 */
	public synchronized long getIntervall() {
		return intervall;
	}

	/**
	 * @param intervall the intervall to set
	 */
	public synchronized void setIntervall(long intervall) {
		this.intervall = intervall;
	}

	/**
	 * @return the callBuffer
	 */
	public synchronized long getCallBuffer() {
		return callBuffer;
	}

	/**
	 * @param callBuffer the callBuffer to set
	 */
	public synchronized void setCallBuffer(long callBuffer) {
		this.callBuffer = callBuffer;
	}

	/**
	 * @return the lastCall
	 */
	public synchronized long getLastCall() {
		return lastCall;
	}

	/**
	 * @return the enabled
	 */
	public synchronized boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public synchronized void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Reset the last call to now.
	 */
	public synchronized void resetLastCall() {
		lastCall =  System.currentTimeMillis();
	}
}
