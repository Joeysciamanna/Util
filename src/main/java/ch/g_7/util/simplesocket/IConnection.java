package ch.g_7.util.simplesocket;

import java.io.IOException;

import ch.g_7.util.common.Closeable;
import ch.g_7.util.common.Openable;

public interface IConnection extends Openable, Closeable {

	byte[] send(byte[] data) throws IOException;

}
