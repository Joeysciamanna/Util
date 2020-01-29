package ch.g_7.util.fdt;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ch.g_7.util.common.Openable;
import ch.g_7.util.fdt.data.Metadata;
import ch.g_7.util.fdt.data.Request;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.exception.ServerException;
import ch.g_7.util.simplesocket.IConnection;

/**
 * Simple FDTConnection
 * Able to send String data to a specific Path (Endpoint)
 * You can also set and read Metadata.
 * 
 * @author Joey Sciamanna
 */
public class FDTConnection implements Openable, Closeable{

	
	private IConnection connection;
	private Metadata metadata;

	/**
	 * Constructor for FDTConnection
	 * @param connection a connection to a FDTServer
	 */
	public FDTConnection(IConnection connection) {
		this.connection = connection;
		this.metadata = new Metadata();
	}
	
	
	/**
	 * Sends the specific data with metadata as json String,
	 * the Json data is getting convertet to UTF_8 Bites and send.
	 * @param data the data to be send
	 * @param path the path (Endpoint) which will recive the data
	 * @return the response from the Server
	 * @throws ServerException if the Server throws errors
	 * @throws IOException if the connection is invalid
	 */
	public Response send(String data, String path) throws ServerException, IOException {
		Request request = new Request(metadata, path, data);
		byte[] responseBytes = connection.send(request.stringify().getBytes(StandardCharsets.UTF_8));
		Response response = new Response(new String(responseBytes,StandardCharsets.UTF_8));

		if(!response.getStatusCode().isSuccessfull()) {
			throw new ServerException(response.getMetadata(), response.getError(), response.getStatusCode());
		}
		return response;
	}
	
	/**
	 * Returns the default Metadata, more attributes can be added to this
	 * @return The default Metadata
	 */
	public Metadata getMetadata() {
		return metadata;
	}


	/**
	 * Closes the connection
	 */
	@Override
	public void close() throws IOException {
		connection.close();
	}


	/**
	 * Opens the connection
	 */
	@Override
	public void open() throws IOException {
		connection.open();
	}
	
	
}
