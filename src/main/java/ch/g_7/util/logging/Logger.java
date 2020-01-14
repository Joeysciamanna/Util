 package ch.g_7.util.logging;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.g_7.util.logging.writer.ILogWriter;

public class Logger implements Closeable {

	private List<ILogWriter> writers;
	private final static Logger instance = new Logger();
	
	private Logger() {
		this.writers = new ArrayList<ILogWriter>();
	}
	
	public final static Logger getInstance() {
		return instance;
	}
	
	public void log(LogLevel level, LogMessage message) {
		logExept(level, message, new ArrayList<ILogWriter>());
	}
	
	private void logExept(LogLevel level, LogMessage message, List<ILogWriter> logWriters) {
		for (ILogWriter logWriter : writers) {
			if(logWriter.isWriting(level) && !logWriters.contains(logWriter)) {
				try {
					logWriter.write(level, message);
				} catch (IOException e) {
					logWriters.add(logWriter);
					logExept(level, new LogMessage("Error at writing log message", e), logWriters);
				}
			}
		}
	}

	public void log(LogLevel level, String message) {
		log(level, new LogMessage(message));
	}

	public void log(LogLevel level, Throwable throwable) {
		log(level, new LogMessage(throwable));
	}

	public void log(LogLevel level, String message, Throwable throwable) {
		log(level, new LogMessage(message, throwable));
	}

	public void addWriter(ILogWriter writer) {
		writers.add(writer);
	}

	public void removeWriter(ILogWriter writer) {
		writers.removeIf((w)->w.getName().equals(writer.getName()));
	}
	

	
	@Override
	public void close() throws IOException {
		for (ILogWriter logWriter : writers) {
			logWriter.close();
		}
	}

}
