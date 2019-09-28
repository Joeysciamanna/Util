package ch.g_7.util.fdt.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import ch.g_7.util.parse.Destringifyable;
import ch.g_7.util.parse.Stringifyable;
import ch.g_7.util.process.Task;

public class Metadata implements Stringifyable{
 
	private Map<String, String> staticData;
	private Map<String, Task<Void, String>> dynamicData;
	private Map<String, String> resolvedData;
	private JSONObject json;
	private boolean write;
	private boolean upToDate;
	
	public Metadata() {
		staticData = new HashMap<String, String>();
		dynamicData = new HashMap<String, Task<Void,String>>();
		json = new JSONObject();
		write = true;
	}
	
	@Destringifyable
	public Metadata(String jsonString) {
		JSONObject json = new JSONObject(jsonString);
		for (String key : JSONObject.getNames(json)) {
			resolvedData.put(key, json.getString(key));
		}
		write = false;
	}
	
	public String stringify() {
		if(!write) {
			throw new UnsupportedOperationException("In reading mode .toJson is not allowed");
		}
		if(!upToDate) {
			for (Entry<String, String> entry : staticData.entrySet()) {
				json.put(entry.getKey(), entry.getValue());
			}
		}
		for (Entry<String, Task<Void, String>> entry : dynamicData.entrySet()) {
			json.put(entry.getKey(), entry.getValue().run(null));
		}
		return json.toString();
	}
	
	public void addStaticData(String key, String value) {
		if(!write) {
			throw new UnsupportedOperationException("You cant and data in reading mode");
		}
		staticData.put(key, value);
		upToDate = false;
	}
	
	public void addDynamicData(String key, Task<Void, String> dataBuilder) {
		if(!write) {
			throw new UnsupportedOperationException("You cant and data in reading mode");
		}
		dynamicData.put(key, dataBuilder);
	}
	
	public String get(String key) {
		if(write) {
			throw new UnsupportedOperationException("You cant read data in writing mode");
		}
		return resolvedData.get(key);
	}
}
