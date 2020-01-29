package ch.g_7.util.properties;

import java.io.IOException;
import java.io.InputStream;

import ch.g_7.util.helper.IOUtil;

public class PropertyProducer {

	private static Settings DEFAULT_PROPERTIES;
	
	public Settings getProperties(InputStream inputStream) throws IOException {
		return new Settings(PropertyParser.fromString(IOUtil.toString(inputStream)));
	}
	
	public Settings getProperties(String txt) {
		return new Settings(PropertyParser.fromString(txt));
	}
	
	public static void setDefaultProperties(Settings properties) {
		if(DEFAULT_PROPERTIES != null) {
			throw new IllegalStateException("Default Properties alredy set");
		}
		DEFAULT_PROPERTIES = properties;
	}
	

	
}
