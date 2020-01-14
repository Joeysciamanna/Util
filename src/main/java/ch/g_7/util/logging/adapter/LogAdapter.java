package ch.g_7.util.logging.adapter;

import ch.g_7.util.logging.LogLevel;
import ch.g_7.util.logging.Logger;

public abstract class LogAdapter implements ILogAdapter {

	protected Logger logger;
	protected LogLevel logLevel;
	

	public LogAdapter(LogLevel logLevel) {
		this.logLevel = logLevel;
	}
	
	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}
	
	@Override
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
}


