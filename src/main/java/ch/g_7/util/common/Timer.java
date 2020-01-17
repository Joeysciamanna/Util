package ch.g_7.util.common;

public class Timer {
	
	private float deltaMillis;
	private long lastloopTime;

	
	public float calculate() {
		double nanoTime = System.nanoTime();
		deltaMillis = (float) ((nanoTime - lastloopTime) / 1000000d);		
		lastloopTime = System.nanoTime();
		return deltaMillis;
	}
	
	public float getDeltaMillis() {
		return deltaMillis;
	}
	
	public int getLPS() {
		return (int) (1000f/deltaMillis);
	}

	public void reset() {
		lastloopTime = System.nanoTime();
	}

}
