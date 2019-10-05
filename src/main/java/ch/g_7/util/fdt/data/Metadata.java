package ch.g_7.util.fdt.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import ch.g_7.util.parse.Destringifyable;
import ch.g_7.util.parse.Stringifyable;
import ch.g_7.util.task.Task;

public class Metadata implements Stringifyable{
 
	private Map<String, Task<Void, String>> dynamicData;
	private JSONObject json;

	
	public Metadata() {
		json = new JSONObject();
		dynamicData = new HashMap<>();
	}
	
	@Destringifyable
	public Metadata(String jsonString) {
		json = new JSONObject(jsonString);
		dynamicData = new HashMap<>();
	}
	
	public String stringify() {
		for (Entry<String, Task<Void, String>> entry : dynamicData.entrySet()) {
			json.put(entry.getKey(), entry.getValue().run(null));
		}
		return json.toString();
	}
	
	public void addStaticData(String key, String value) {
		json.put(key, value);
	}
	
	public void addDynamicData(String key, Task<Void, String> dataBuilder) {
		dynamicData.put(key, dataBuilder);
	}
	
	public String get(String key) {
		return json.getString(key);
	}
	

}
