package ch.g_7.util.task;

/**
 * This Class can be used to call a task all x milliseconds,
 * with on optional callBuffer which allows the Task to be called multiple Times a run
 * instead of once every interval. This can result in better Performance, because it only
 * need to be called every interval * callBuffer instead of every interval.
 * The Task is not called automatically, this Task must be called to try execute the wrapped
 * Task, if the interval is then reached, the Task will be called.
 * If the Wrapped Task missed calls, then the Task will be called for every missed call.
 *
 * @param <I> the Input type of this Task
 *
 * @author Joey Sciamanna
 */
public class TaskIntervalBuffer<I> implements Task<I, Void> {

	private Task<I, Void> task;
	
	private long intervall;
	private long callBuffer;
	private long lastCall;
	private boolean enabled;
	
	public TaskIntervalBuffer(Task<I, Void> task, long intervall, long callBuffer) {
		this(task, intervall);
		this.callBuffer = callBuffer;
	}
	
	public TaskIntervalBuffer(Task<I, Void> task, long intervall) {
		this.task = task;
		this.intervall = intervall;
		enabled = true;
		resetLastCall();
	}
	
	/**
	 * Tries running the wrapped Task,
	 * if the interval is reached the Task will be called for every missed call.
	 * if the interval is not reached, the task wont be called.
	 */
	@Override
	public Void run(I t) {
		if(enabled) {
			long actTime = System.currentTimeMillis();
			long div = actTime - lastCall;
			long missedCalls;
			if((missedCalls = div/intervall)>0) {
				for(int i = 0; i < (missedCalls+callBuffer); i++) {
					task.run(t);
				}
				lastCall = actTime - (div%intervall) + (callBuffer*intervall);
			}
		} else {
			resetLastCall();
		}
		return null;
	}

	
	
	/**
	 * @return the intervall
	 */
	public long getIntervall() {
		return intervall;
	}

	/**
	 * @param intervall the intervall to set
	 */
	public void setIntervall(long intervall) {
		this.intervall = intervall;
	}

	/**
	 * @return the callBuffer
	 */
	public long getCallBuffer() {
		return callBuffer;
	}

	/**
	 * @param callBuffer the callBuffer to set
	 */
	public void setCallBuffer(long callBuffer) {
		this.callBuffer = callBuffer;
	}

	/**
	 * @return the lastCall
	 */
	public long getLastCall() {
		return lastCall;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Reset the last call to now.
	 */
	public void resetLastCall() {
		lastCall =  System.currentTimeMillis();
	}
}
