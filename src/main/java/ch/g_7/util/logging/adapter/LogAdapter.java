package ch.g_7.util.logging.adapter;

import ch.g_7.util.logging.LogLevel;
import ch.g_7.util.logging.Logger;

public abstract class LogAdapter {

	protected final Logger logger;
	protected LogLevel logLevel;
	
	public LogAdapter(Logger logger, LogLevel logLevel) {
		this.logger = logger;
		this.logLevel = logLevel;
	}
	
	public LogAdapter(LogLevel logLevel) {
		this(Logger.getInstance(), logLevel);
	}
}


