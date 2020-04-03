package ch.g_7.util.fdt.data;

import org.json.JSONObject;

import ch.g_7.util.fdt.exception.StatusCode;
import ch.g_7.util.parse.Destringifiable;
import ch.g_7.util.parse.Stringifyable;

/**
 * Response Object, containing metadata, statusCode, error (if something went wrong), and the data.
 * 
 * @author Joey Sciamanna
 */
public class Response implements Stringifyable{

	private Metadata metadata;
	private StatusCode statusCode;
	private String error;
	private String data;
	
	
	public Response(StatusCode statusCode, String error, String data) {
		this.statusCode = statusCode;
		this.error = error;
		this.data = data;
	}
	
	/**
	 * Create a response object from JSON string
	 * @param jsonString The JSON string
	 */
	@Destringifiable
	public Response(String jsonString) {
		JSONObject json = new JSONObject(jsonString);
		this.metadata = new Metadata(json.getString("metadata")); 
		this.statusCode = json.getEnum(StatusCode.class ,"status-code");
		this.error = json.getString("error");
		this.data = json.getString("data");
	}
	
	/**
	 * Parses this to a JSON string
	 */
	@Override
	public String stringify() {
		JSONObject json = new JSONObject();
		json.put("metadata", metadata.stringify());
		json.put("status-code", statusCode);
		json.put("error", error);
		json.put("data", data);
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
	
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	
	public StatusCode getStatusCode() {
		return statusCode;
	}
	
}
