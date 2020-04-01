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
	
	public void log(LogMessage message) {
		logExept(message, new ArrayList<ILogWriter>());
	}
	
	private void logExept(LogMessage message, List<ILogWriter> logWriters) {
		for (ILogWriter logWriter : writers) {
			if(logWriter.isWriting(message.getLevel()) && !logWriters.contains(logWriter)) {
				try {
					logWriter.write(message);
				} catch (IOException e) {
					logWriters.add(logWriter);
					logExept(new LogMessage("Error at writing log message", e, LogLevel.ERROR), logWriters);
				}
			}
		}
	}

	public void log(LogLevel level, String message) {
		log(new LogMessage(message, level));
	}

	public void log(LogLevel level, Throwable throwable) {
		log(new LogMessage(throwable, level));
	}

	public void log(LogLevel level, String message, Throwable throwable) {
		log(new LogMessage(message, throwable, level));
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
