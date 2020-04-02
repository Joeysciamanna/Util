package ch.g_7.util.fdt.function;

import ch.g_7.util.fdt.data.Request;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.exception.FDTException;

/**
 * Superclass of every Receiver
 * 
 * @author Joey Sciamanna
 */
public abstract class Receiver extends EndPoint {

	public Receiver(String endpoint) {
		super(endpoint);
	}

	public Receiver() { }

	/**
	 * Called whenever a request to this is received
	 * @param request The received request
	 * @return The response
	 * @throws FDTException Thrown when a error occurs
	 */
	public abstract Response receive(Request request) throws FDTException;
}
