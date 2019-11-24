package ch.g_7.util.fdt.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import ch.g_7.util.parse.Destringifyable;
import ch.g_7.util.parse.Stringifyable;
import ch.g_7.util.task.Task;

/**
 * Metadata of every request / response, multiple attributes can be set.
 * 
 * @author Joey Sciamanna
 */
public class Metadata implements Stringifyable{
 
	private Map<String, Task<Void, String>> dynamicData;
	private JSONObject json;

	
	public Metadata() {
		json = new JSONObject();
		dynamicData = new HashMap<>();
	}
	
	/**
	 * Create a metadata object from JSON string
	 * @param jsonString The JSON string
	 */
	@Destringifyable
	public Metadata(String jsonString) {
		json = new JSONObject(jsonString);
		dynamicData = new HashMap<>();
	}
	
	/**
	 * Parses the attributes to a JSON String
	 */
	public String stringify() {
		for (Entry<String, Task<Void, String>> entry : dynamicData.entrySet()) {
			json.put(entry.getKey(), entry.getValue().run(null));
		}
		return json.toString();
	}
	
	/**
	 * Adds a static data attribute
	 * @param key The key of the attribute
	 * @param value The value of the Attribute
	 */
	public void addStaticData(String key, String value) {
		json.put(key, value);
	}
	
	/**
	 * Adds a dynamic data attribute, by which
	 * the dataBuilder gets invoked whenever this gets build / stringified
	 * @param key The key of the data
	 * @param dataBuilder the data builder.
	 */
	public void addDynamicData(String key, Task<Void, String> dataBuilder) {
		dynamicData.put(key, dataBuilder);
	}
	
	/**
	 * Gets the attribute value by its key 
	 * @param key The key of the attribute
	 * @return The value of the attribute
	 */
	public String get(String key) {
		if(dynamicData.containsKey(key)) {
			return dynamicData.get(key).run(null);
		}
		return json.getString(key);
	}
	

}
