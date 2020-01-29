package ch.g_7.util.fdt.exception;

/**
 * Status codes to indicate the status of a request,
 * Similar to HTTP Status codes
 * 
 * @author Joey Sciamanna
 */
public enum StatusCode {
	
	/**
	 * All Fine
	 */
	SUCCESS(true),
	
	/**
	 * Was able to handle it, but something wasn't as expected
	 */
	PASSTHROUGH(true),
	
	/**
	 * The requested path / destination was not found,
	 * probably because there is no receiver register
	 * at the requested path / destination
	 */
	RECEIVER_NOT_FOUND(false),
	
	/**
	 * The request parameters where wrong
	 */
	INVALLID_REQUEST_PARAMS(false);
	
	
	private boolean success;
	
	private StatusCode(boolean success) {
		this.success = success;
	}
	
	public boolean isSuccessfull() {
		return success;
	}
	
}













