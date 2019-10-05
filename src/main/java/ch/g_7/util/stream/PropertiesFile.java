package ch.g_7.util.stream;

import java.util.Map;

import ch.g_7.util.parse.Destringifyable;
import ch.g_7.util.parse.Stringifyable;

public class PropertiesFile implements Stringifyable {

	private String data;
	private Map<String, String> properties;

	@Destringifyable
	public PropertiesFile(String data) {
		this.data = data;
	}

	public String get(String key, String defaultValue) {
		if(properties.containsKey(key)) {
			return properties.get(key);
		}
		String value = defaultValue;
		for(String line : data.split("\n")) {
			if(line.startsWith(key)) {
				String[] parts = line.split("=");
				if(parts.length > 1 && !parts[1].isBlank()) {
					value = parts[1];
					break;
				}
			}
		}
		properties.put(key, value);
		return value;
	}

	public void set(String key, String value) {
		if(properties.get(key).equals(value)) {
			return;
		}
		StringBuilder newData = new StringBuilder();
		for(String line : data.split("\n")) {
			if(line.startsWith(key)) {
				newData.append(key).append("=").append(value).append("\n");
				break;
			}else {
				newData.append(line);
			}
		}
		data = newData.toString();
		properties.put(key, value);
	}
	
	@Override
	public String stringify() {
		return data;
	}

}
