package ch.g_7.util.task;

import ch.g_7.util.common.Timer;

public abstract class Loop implements Runnable {

	private Thread thread;
	private Timer timer;
	private boolean running;
	private boolean paused;
	
	public Loop() {
		this.timer = new Timer();
	}

	@Override
	public final void run() {
		onStart();
		timer.reset();
		while (running) {
			timer.calculate();
			if(!paused) {
				run(timer.getDeltaMillis());
			}
		}
		onStop();
	}
	
	protected abstract void run(float deltaMillis);
	
	protected void onStart() {}
	protected void onStop() {}
	
	protected final void setRunning(boolean running) {
		if (running && !this.running) {
			this.running = true;
			thread = new Thread(this);
			thread.start();
		} else if (!running && this.running) {
			this.running = false;
			thread = null;
		}
	}
	
	public void start() {
		setRunning(true);
	}

	public void stop() {
		setRunning(false);
	}
	
	protected void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	public void pause() {
		setPaused(true);
	}
	
	public void resume() {
		setPaused(false);
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	int getLPS(){
		return timer.getLPS();
	}
}
