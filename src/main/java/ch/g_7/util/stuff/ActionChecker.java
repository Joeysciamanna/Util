package ch.g_7.util.stuff;

public abstract class ActionChecker implements Runnable, Passable{

	private boolean running;
	private boolean pause;
	private Thread thread;
	private SecureRunner<Void,Void> sleeper;
	private long intervall;
	
	
	public ActionChecker() {
		sleeper = new SecureRunner<Void, Void>(()->Thread.sleep(intervall));
	}
	

	@Override
	public final void run() {
		onStart();
		while (running) {
			if(check() && !pause) {
				onAction();
			}
			sleeper.run();
		}
		onClose();
	}
	
	public final void setRunning(boolean running) {
		if(!this.running && running) {
			thread = new Thread(this);
			this.running = true;
			thread.start();
		}else if(this.running && !running) {
			this.running = false;
		}
	}
	
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
	protected abstract void onStart();
	protected abstract void onClose();
	protected abstract void onAction();
	protected abstract boolean check();
	
	public void setIntervall(long intervall) {
		this.intervall = intervall;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public boolean isPause() {
		return pause;
	}
	
	@Override
	public void close() throws Exception {
		setRunning(false);
	}
	
	@Override
	public void open() throws Exception {
		setRunning(true);
	}

}
