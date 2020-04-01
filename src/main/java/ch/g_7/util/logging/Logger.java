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

	public void fatal(String message){
		log(new LogMessage(message, LogLevel.FATAL));
	}

	public void fatal(Throwable throwable){
		log(new LogMessage(throwable, LogLevel.FATAL));
	}

	public void fatal(String message, Throwable throwable){
		log(new LogMessage(message, throwable, LogLevel.FATAL));
	}

	public void error(String message){
		log(new LogMessage(message, LogLevel.ERROR));
	}

	public void error(Throwable throwable){
		log(new LogMessage(throwable, LogLevel.ERROR));
	}

	public void error(String message, Throwable throwable){
		log(new LogMessage(message, throwable, LogLevel.ERROR));
	}

	public void warning(String message){
		log(new LogMessage(message, LogLevel.WARNING));
	}

	public void warning(Throwable throwable){
		log(new LogMessage(throwable, LogLevel.WARNING));
	}

	public void warning(String message, Throwable throwable){
		log(new LogMessage(message, throwable, LogLevel.WARNING));
	}

	public void info(String message){
		log(new LogMessage(message, LogLevel.INFO));
	}

	public void info(Throwable throwable){
		log(new LogMessage(throwable, LogLevel.INFO));
	}

	public void info(String message, Throwable throwable){
		log(new LogMessage(message, throwable, LogLevel.FATAL));
	}

	public void debug(String message){
		log(new LogMessage(message, LogLevel.DEBUG));
	}

	public void debug(Throwable throwable){
		log(new LogMessage(throwable, LogLevel.DEBUG));
	}

	public void debug(String message, Throwable throwable){
		log(new LogMessage(message, throwable, LogLevel.DEBUG));
	}

	public void wtf(String message){
		log(new LogMessage(message, LogLevel.WTF));
	}

	public void wtf(Throwable throwable){
		log(new LogMessage(throwable, LogLevel.WTF));
	}

	public void wtf(String message, Throwable throwable) {
		log(new LogMessage(message, throwable, LogLevel.WTF));
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

	public void log(LogMessage message) {
		logExept(message, new ArrayList<>());
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
