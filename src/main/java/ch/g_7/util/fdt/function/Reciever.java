package ch.g_7.util.fdt.function;

import ch.g_7.util.fdt.data.Request;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.exception.FDTException;

/**
 * Superclass of every Receiver
 * 
 * @author Joey Sciamanna
 */
public abstract class Reciever extends Function {

	/**
	 * Called whenever a request to this is received
	 * @param request The received request
	 * @return The response
	 * @throws FDTException
	 */
	public abstract Response recieve(Request request) throws FDTException;
}
