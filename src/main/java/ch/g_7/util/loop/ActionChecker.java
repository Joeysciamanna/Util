package ch.g_7.util.loop;

import ch.g_7.util.task.checked.Checked;

public abstract class ActionChecker extends Loop {


	private Runnable sleeper;
	
	
	public ActionChecker() {
		this(1000);
	}
	
	public ActionChecker(int intervallMillis) {
		sleeper = Checked.checkedRunnable(()->Thread.sleep(intervallMillis));
	}
	
	@Override
	public void run(float deltaMillis) {
		if(actionOccured()) {
			onAction();
		}
		sleeper.run();
	}
	
	protected abstract void onAction();
	protected abstract boolean actionOccured();
	


}
