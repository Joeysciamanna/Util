package ch.g_7.util.fdt.data;

import org.json.JSONObject;

import ch.g_7.util.fdt.exception.StatusCode;
import ch.g_7.util.parse.Destringifyable;
import ch.g_7.util.parse.Stringifyable;

public class Response implements Stringifyable{

	private Metadata metadata;
	private StatusCode statusCode;
	private String error;
	private String data;
	
	
	public Response(Metadata metadata, StatusCode statusCode, String error, String data) {
		this.metadata = metadata;
		this.statusCode = statusCode;
		this.error = error;
		this.data = data;
	}
	
	@Destringifyable
	public Response(String jsonString) {
		JSONObject json = new JSONObject(jsonString);
		this.metadata = new Metadata(json.getString("metadata")); 
		this.statusCode = json.getEnum(StatusCode.class ,"status_code");
		this.error = json.getString("error");
		this.data = json.getString("data");
	}
	
	

	public String stringify() {
		JSONObject json = new JSONObject();
		json.put("metadata", metadata.stringify());
		json.put("status_code", statusCode);
		json.put("error", error);
		json.put(data, data);
		return json.toString();
	}
	
	public String getData() {
		return data;
	}
	
	public String getError() {
		return error;
	}
	
	public Metadata getMetadata() {
		return metadata;
	}
	
	public StatusCode getStatusCode() {
		return statusCode;
	}
	
}
