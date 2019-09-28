package ch.g_7.util.simplesocket;

import ch.g_7.util.stuff.Passable;

public interface IConnection extends Passable{

	public byte[] send(byte[] data);
	
}
