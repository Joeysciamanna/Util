package ch.g_7.util.logging.writer;

import java.io.Closeable;
import java.io.IOException;

import ch.g_7.util.logging.ILogFormator;
import ch.g_7.util.logging.LogLevel;
import ch.g_7.util.logging.LogMessage;

public interface ILogWriter extends Closeable {

	boolean isWriting(LogLevel level);
	
	void write(LogMessage message) throws IOException;
	
	default void setFormator(ILogFormator formator) {
		throw new IllegalStateException("Formator is not supported on " + getName());
	}
	
	String getName();

}
