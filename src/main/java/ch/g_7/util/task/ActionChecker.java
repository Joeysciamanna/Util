package ch.g_7.util.task;

public abstract class ActionChecker extends Loop {


	private SecureRunner<Void,Void> sleeper;
	
	
	public ActionChecker() {
		this(1000);
	}
	
	public ActionChecker(int intervallMillis) {
		sleeper = new SecureRunner<Void, Void>(()->Thread.sleep(intervallMillis)).throwException(null);
	}
	
	@Override
	public void run(float deltaMillis) {
		if(actionOccured()) {
			onAction();
		}
		sleeper.get();
	}
	
	protected abstract void onAction();
	protected abstract boolean actionOccured();
	


}
