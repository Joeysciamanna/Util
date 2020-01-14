package ch.g_7.util.logging.adapter;

import java.io.IOException;
import java.io.OutputStream;

import ch.g_7.util.logging.LogLevel;
import ch.g_7.util.logging.Logger;

public class OutputSteamAdapter extends OutputStream {

	private final Logger logger;
	private LogLevel logLevel;
	private StringBuilder builder;
	
	public OutputSteamAdapter(Logger logger, LogLevel logLevel) {
		this.logLevel = logLevel;
		this.builder = new StringBuilder();
		this.logger = logger;
	}
	
	public OutputSteamAdapter(LogLevel logLevel) {
		this(Logger.getInstance(), logLevel);
	}
	
	@Override
	public void write(int b) throws IOException {
		builder.append((char)b);
	}

	@Override
	public void flush() throws IOException {
		logger.log(logLevel, builder.toString());
		builder.setLength(0);
	}
	

}
