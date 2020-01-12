package ch.g_7.util.logging;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogMessage {

	private final String message;
	private final Throwable throwable;
	
	public LogMessage(String message, Throwable throwable) {
		this.message = message;
		this.throwable = throwable;
	}

	public LogMessage(String message) {
		this(message, null);
	}
	
	public LogMessage(Throwable throwable) {
		this(null, throwable);
	}
	
	public String getDetails() {
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
	

}
