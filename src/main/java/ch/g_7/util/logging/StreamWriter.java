package ch.g_7.util.logging;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

public class StreamWriter extends AbstractLogWriter implements Closeable {

	private OutputStream outputStream;
	
	public StreamWriter(OutputStream outputStream, String name, LogLevel... levels) {
		super(name, levels);
		this.outputStream = outputStream;
	}

	@Override
	public void write(LogLevel level, String log) throws IOException {
		outputStream.write(log.getBytes());
		outputStream.flush();
	}

	public void close() throws IOException {
		outputStream.close();
	}



}
