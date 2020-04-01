package ch.g_7.util.logging;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogMessage {

	private final LogLevel level;
	private final String message;
	private final Throwable throwable;
	
	public LogMessage(String message, Throwable throwable, LogLevel level) {
		this.message = message;
		this.throwable = throwable;
		this.level = level;
	}

	public LogMessage(String message, LogLevel level) {
		this(message, null, level);
	}
	
	public LogMessage(Throwable throwable, LogLevel level) {
		this(null, throwable, level);
	}
	
	public String getStackTrace() {
		if(throwable != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			throwable.printStackTrace(pw);
			return sw.toString();
		}
		return "";
	}
	
	public String getSimpleMessage() {
		return message == null ? throwable.getMessage() : message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Throwable getThrowable() {
		return throwable;
	}
	
	
	public LogLevel getLevel() {
		return level;
	}

}
