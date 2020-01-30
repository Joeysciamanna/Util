package ch.g_7.util.properties;

import java.io.IOException;
import java.io.InputStream;

import ch.g_7.util.helper.IOUtil;

public class PropertyProducer {

	private final static IProperties APP_CONFIG = new Properties();
	private static IProperties DEFAULT_PROPERTIES;
	
	public IProperties getProperties(InputStream inputStream) throws IOException {
		return new Properties(PropertyParser.fromString(IOUtil.toString(inputStream)));
	}
	
	public IProperties getProperties(String txt) {
		return new Properties(PropertyParser.fromString(txt));
	}
	
	public static void setDefaultProperties(IProperties properties) {
		if(DEFAULT_PROPERTIES != null) {
			throw new IllegalStateException("Default Properties alredy set");
		}
		DEFAULT_PROPERTIES = properties;
	}
	
	public static IProperties getDefaultProperties() {
		return DEFAULT_PROPERTIES;
	}
	
	public static IProperties getAppConfig() {
		return APP_CONFIG;
	}

	
}
