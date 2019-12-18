package ch.g_7.util.logging;

import java.io.IOException;

public interface ILogWriter {

	boolean isWriting(LogLevel level);
	
	void write(LogLevel level, LogMessage message) throws IOException;
	
	default void setFormator(ILogFormator formator) {
		throw new IllegalStateException("Formator is not supported on " + getName());
	}
	
	String getName();

}
