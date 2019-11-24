package ch.g_7.util.fdt.exception;

/**
 * Exception for whenever something goes wrong during handling the request,
 * thrown serverside.
 * 
 * @author Joey Sciamanna
 */
public class FDTException extends RuntimeException{

	private static final long serialVersionUID = 3596204354046325528L;
	private StatusCode statusCode;
	
	
	public FDTException(String message, StatusCode statusCode) {
		super(message);
		this.statusCode = statusCode;
	}
	
	public FDTException(Exception e, String message, StatusCode statusCode) {
		super(message, e);
		this.statusCode = statusCode;
	}
	
	public StatusCode getStatusCode() {
		return statusCode;
	}
}
