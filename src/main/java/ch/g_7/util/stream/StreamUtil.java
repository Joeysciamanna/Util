package ch.g_7.util.stream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class StreamUtil {

	public static byte[] readBytes(String path, Object resourceLocator) throws IOException {
		InputStream stream = resourceLocator.getClass().getClassLoader().getResourceAsStream(path);
		byte[] bytes = new byte[stream.available()];
		stream.read(bytes);
		return bytes;
	}

	public static String readString(String path, Object resourceLocator) throws IOException {
		return new String(readBytes(path, resourceLocator), StandardCharsets.UTF_8);
	}

	public static void writeBytes(String path, byte[] data, boolean append) throws IOException {
		writeString(path, new String(data, StandardCharsets.UTF_8), append);
	}

	public static void writeString(String path, String data, boolean append) throws IOException {
		FileWriter fileWriter = new FileWriter(new File(path), append);
		fileWriter.write(data);
		fileWriter.close();
	}
}
