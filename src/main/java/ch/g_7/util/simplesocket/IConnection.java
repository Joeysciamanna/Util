package ch.g_7.util.simplesocket;

import java.io.IOException;

import ch.g_7.util.stuff.Passable;

public interface IConnection extends Passable{

	public byte[] send(byte[] data) throws IOException;
	
	@Override
	void close() throws IOException;
	
	@Override
	void open() throws IOException;
}
