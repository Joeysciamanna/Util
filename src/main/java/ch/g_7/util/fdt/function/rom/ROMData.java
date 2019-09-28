package ch.g_7.util.fdt.function.rom;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;

import ch.g_7.util.parse.Destringifyable;
import ch.g_7.util.parse.Stringifyable;

public class ROMData implements Stringifyable{

	private long objId;
	private String methodName;
	private List<String> args;
	
	
	
	public ROMData(long objId, String methodName, List<String> args) {
		this.objId = objId;
		this.methodName = methodName;
		this.args = args;
	}

	@Destringifyable
	public ROMData(String json) {
		JSONObject jsonObject = new JSONObject(json);
		objId = jsonObject.getLong("obj-id");
		methodName = jsonObject.getString("method-name");
		args = jsonObject.getJSONArray("args").toList().stream().map((o) -> o.toString()).collect(Collectors.toList());
	}
	
	@Override
	public String stringify() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("obj-id", objId);
		jsonObject.put("method-name", methodName);
		jsonObject.put("args", args);
		return jsonObject.toString();
	}
	
	public List<String> getArgs() {
		return args;
	}
	
	public String getMethodName() {
		return methodName;
	}
	
	public long getObjId() {
		return objId;
	}

	
}
