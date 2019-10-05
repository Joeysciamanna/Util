package ch.g_7.util.fdt.exception;


public enum StatusCode {
	SUCCESS,
	PASSTHROUGH,
	FUNCTION_NOT_FOUND,
	INVALLID_REQUEST_PARAMS;
	
	
	public static boolean ok(StatusCode code) {
		return code == SUCCESS || code == PASSTHROUGH;
	}
}
