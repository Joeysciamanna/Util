package ch.g_7.util.fdt.data;

import org.json.JSONObject;

import ch.g_7.util.parse.Destringifiable;
import ch.g_7.util.parse.Stringifyable;

/**
 * Request Object, contains metadata, path (destination) and the data
 * 
 * @author Joey Sciamannaw
 */
public class Request implements Stringifyable{

	private Metadata metadata;
	private String path;
	private String data;
	
	
	public Request(Metadata metadata, String path, String data) {
		this.metadata = metadata;
		this.path = path;
		this.data = data;
	}
	
	/**
	 * Create a request object from JSON string
	 * @param jsonString The JSON string
	 */
	@Destringifiable
	public Request(String jsonString) {
		JSONObject json = new JSONObject(jsonString);
		this.metadata = new Metadata(json.getString("metadata"));
		this.path = json.getString("path");
		this.data = json.getString("data");
	}
	
	/**
	 * Parses this to a JSON object
	 */
	@Override
	public String stringify() {
		JSONObject json = new JSONObject();
		json.put("metadata", metadata.stringify());
		json.put("path", path);
		json.put("data", data);
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
