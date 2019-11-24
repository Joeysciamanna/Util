package ch.g_7.util.fdt.function;

import java.io.IOException;

import ch.g_7.util.fdt.FDTConnection;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.exception.ServerException;

/**
 * Superclass of every sender.
 * 
 * @author Joey Sciamanna
 */
public abstract class Sender extends Function{

	private FDTConnection connection;
	
	public Sender(FDTConnection connection, String endpoint) {
		super(endpoint);
		this.connection = connection;
	}
	
	public Sender(FDTConnection connection) {
		this.connection = connection;
	}
	
	/**
	 * Sends the given data
	 * @param data The data to send
	 * @return The response
	 * @throws ServerException Thrown when a error occurs at the server
	 */
	protected final Response send(String data) throws ServerException {
		try {
			return connection.send(data, getPath());
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
