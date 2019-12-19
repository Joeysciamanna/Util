package ch.g_7.util.logging;

import java.io.Closeable;
import java.io.IOException;

public interface ILogWriter extends Closeable {

	boolean isWriting(LogLevel level);
	
	void write(LogLevel level, LogMessage message) throws IOException;
	
	default void setFormator(ILogFormator formator) {
		throw new IllegalStateException("Formator is not supported on " + getName());
	}
	
	String getName();

}
