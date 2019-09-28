package ch.g_7.util.fdt;

import java.nio.charset.StandardCharsets;

import ch.g_7.util.fdt.data.Metadata;
import ch.g_7.util.fdt.data.Request;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.exception.ServerException;
import ch.g_7.util.fdt.exception.StatusCode;
import ch.g_7.util.simplesocket.IConnection;
import ch.g_7.util.stuff.Passable;

public class FDTConnection implements Passable{

	private IConnection connection;
	private Metadata metadata;
	
	public FDTConnection(IConnection connection) {
		this.connection = connection;
		this.metadata = new Metadata();
	}
	
	
	public Response send(String data, String path) throws ServerException {
		Request request = new Request(metadata, path, data);
		byte[] responseBytes = connection.send(request.toJson().getBytes(StandardCharsets.UTF_8));
		Response response = new Response(new String(responseBytes,StandardCharsets.UTF_8));

		if(response.getStatusCode().equals(StatusCode.SUCCESS) ) {
			throw new ServerException(response.getMetadata(), response.getError(), response.getStatusCode());
		}
		
		return response;
	}
	
	
	public Metadata getMetadata() {
		return metadata;
	}


	@Override
	public void close() throws Exception {
		connection.close();
	}


	@Override
	public void open() throws Exception {
		connection.open();
	}
	
	
}
