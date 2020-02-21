package ch.g_7.util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IOUtil {

	public static byte[] readInternalBytes(String path, Object resourceLocator) {
		try {
			return toBytes(getInternalInputStream(path, resourceLocator));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String readInternalString(String path, Object resourceLocator) {
		return new String(readInternalBytes(path, resourceLocator));
	}

	public static InputStream getInternalInputStream(String path, Object resourceLocator) {
		InputStream inputStream = resourceLocator.getClass().getClassLoader().getResourceAsStream(path);
		if(inputStream == null) {
			throw new RuntimeException("File ["+path+"] not found");
		}
		return inputStream;
	}
	
	
	public static byte[] readExternalBytes(String path) throws IOException {
		return toBytes(getExternalInputStream(path));
	}
	
	public static String readExternalString(String path) throws IOException {
		return new String(readExternalBytes(path));
	}
	
	public static byte[] toBytes(InputStream inputStream) throws IOException {
		byte[] bytes = new byte[inputStream.available()];
		inputStream.read(bytes);
		inputStream.close();
		return bytes;
	}
	
	public static int[] toUBytes(InputStream inputStream) throws IOException {
		byte[] bytes = new byte[inputStream.available()];
		int[] uBytes = new int [inputStream.available()];
		inputStream.read(bytes);
		for (int i = 0; i < bytes.length; i++) {
			uBytes[i] = bytes[i] & 0xff;
		}
		inputStream.close();
		return uBytes;
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
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		FileOutputStream fop = new FileOutputStream(file);
		return fop;
	}
	
	public static boolean doesFileExist(String path) {
		return new File(path).exists();
	}

}
