package ch.g_7.util.logging.adapter;

import java.lang.Thread.UncaughtExceptionHandler;

import ch.g_7.util.logging.LogLevel;

public class UncaughtExceptionHandlerAdapter extends LogAdapter implements UncaughtExceptionHandler {

	
	public UncaughtExceptionHandlerAdapter(LogLevel logLevel) {
		super(logLevel);
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		logger.log(logLevel, e);
	}

}
