package ch.g_7.util.simplesocket;

import java.io.Closeable;
import java.io.IOException;

import ch.g_7.util.common.Openable;

public interface IConnection extends Openable, Closeable{

	public byte[] send(byte[] data) throws IOException;
	
	@Override
	void close() throws IOException;
	
	@Override
	void open() throws IOException;
}
