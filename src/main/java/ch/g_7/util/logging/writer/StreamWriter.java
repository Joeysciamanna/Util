package ch.g_7.util.logging.writer;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

import ch.g_7.util.logging.LogLevel;

public class StreamWriter extends AbstractLogWriter implements Closeable {

	private OutputStream outputStream;
	
	public StreamWriter(OutputStream outputStream, String name, LogLevel... levels) {
		super(name, levels);
		this.outputStream = outputStream;
	}

	@Override
	public void write(String log) throws IOException {
		outputStream.write(log.getBytes());
		outputStream.flush();
	}

	public void close() throws IOException {
		outputStream.close();
	}



}
