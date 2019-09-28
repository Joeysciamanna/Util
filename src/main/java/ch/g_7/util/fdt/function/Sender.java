package ch.g_7.util.fdt.function;

import ch.g_7.util.fdt.FDTConnection;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.exception.ServerException;

public abstract class Sender extends Function{

	private FDTConnection connection;
	
	public Sender(FDTConnection connection, String endpoint) {
		super(endpoint);
		this.connection = connection;
	}
	
	public Sender(FDTConnection connection) {
		this.connection = connection;
	}
	
	protected final Response send(String data) throws ServerException {
		return connection.send(data, getPath());
	}

}
