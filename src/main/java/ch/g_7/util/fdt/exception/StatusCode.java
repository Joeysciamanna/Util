package ch.g_7.util.fdt.exception;

/**
 * Status codes to indicate the status of a request,
 * Similar to HTTP Status codes
 * 
 * @author Joey Sciamanna
 */
//TODO adapt http error codes
public enum StatusCode {
	
	/**
	 * All Fine
	 */
	SUCCESS,
	
	/**
	 * Was able to handle it, but something wasn't as expected
	 */
	PASSTHROUGH,
	
	/**
	 * The requested path / destination was not found,
	 * probably because there is no receiver register
	 * at the requested path / destination
	 */
	RECEIVER_NOT_FOUND,
	
	/**
	 * The request parameters where wrong
	 */
	INVALLID_REQUEST_PARAMS;
	
	
}
