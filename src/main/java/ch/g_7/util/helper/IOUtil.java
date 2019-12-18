package ch.g_7.util.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IOUtil {

	public static byte[] readInternalBytes(String path, Object resourceLocator) throws IOException {
		return toBytes(getInternalInputStream(path, resourceLocator));
	}

	public static String readInternalString(String path, Object resourceLocator) throws IOException {
		return new String(readInternalBytes(path, resourceLocator));
	}

	public static InputStream getInternalInputStream(String path, Object resourceLocator) throws IOException {
		return resourceLocator.getClass().getClassLoader().getResourceAsStream(path);
	}
	
	
	public static byte[] readExternalBytes(String path) throws IOException {
		return toBytes(getExternalInputStream(path));
	}
	
	public static String readExternalString(String path) throws IOException {
		return new String(readExternalString(path));
	}
	
	public static byte[] toBytes(InputStream inputStream) throws IOException {
		byte[] bytes = new byte[inputStream.available()];
		inputStream.read(bytes);
		inputStream.close();
		return bytes;
	}
	
	public static String toString(InputStream inputStream) throws IOException {
		return new String(toBytes(inputStream));
	}
	
	public static InputStream getExternalInputStream(String path) throws IOException {
	    return new FileInputStream(new File(path));
	}
	
	
	public static void writeExternalBytes(String path, byte[] data) throws IOException {
		writeExternalString(path, new String(data));
	}

	public static void writeExternalString(String path, String data) throws IOException {
		OutputStream stream = getExternalOutputStream(path);
		stream.write(data.getBytes());
		stream.close();
	}
	
	public static OutputStream getExternalOutputStream(String path) throws IOException {
		File file = new File(path);
		FileOutputStream fop = new FileOutputStream(file);
		if (!file.exists()) {
			file.createNewFile();
		}
		return fop;
	}
	

}
