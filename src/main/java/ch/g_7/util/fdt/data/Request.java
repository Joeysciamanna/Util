package ch.g_7.util.fdt.data;

import org.json.JSONObject;

import ch.g_7.util.parse.Destringifyable;

public class Request {

	private Metadata metadata;
	private String path;
	private String data;
	
	
	public Request(Metadata metadata, String path, String data) {
		this.metadata = metadata;
		this.path = path;
		this.data = data;
	}
	
	@Destringifyable
	public Request(String jsonString) {
		JSONObject json = new JSONObject(jsonString);
		this.metadata = new Metadata(json.getString("metadata"));
		this.path = json.getString("path");
		this.data = json.getString("data");
	}
	
	
	
	public String toJson() {
		JSONObject json = new JSONObject();
		json.put("metadata", metadata.stringify());
		json.put("path", path);
		json.put(data, data);
		return json.toString();
	}
	
	public String getData() {
		return data;
	}
	
	public Metadata getMetadata() {
		return metadata;
	}
	
	public String getPath() {
		return path;
	}
	
}
