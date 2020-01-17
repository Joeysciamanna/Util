package ch.g_7.util.common;


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
		onClose();
	}
	
	public abstract void run(float deltaMillis);
	
	public void onStart() {}
	public void onClose() {}
	
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
	
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	public void pause() {
		setPaused(true);
	}
	
	public void resume() {
		setPaused(false);
	}
	
	int getLPS(){
		return timer.getLPS();
	}
}
