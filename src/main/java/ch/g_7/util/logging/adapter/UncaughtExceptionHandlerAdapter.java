package ch.g_7.util.logging.adapter;

import java.lang.Thread.UncaughtExceptionHandler;

import ch.g_7.util.logging.LogLevel;
import ch.g_7.util.logging.Logger;

public class UncaughtExceptionHandlerAdapter extends LogAdapter implements UncaughtExceptionHandler {


	public UncaughtExceptionHandlerAdapter(Logger logger) {
		super(logger, LogLevel.FATAL);
	}
	
	public UncaughtExceptionHandlerAdapter() {
		super(LogLevel.FATAL);
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		logger.log(logLevel, e);
	}

}
