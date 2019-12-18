package ch.g_7.util.fdt;


import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import ch.g_7.util.fdt.data.Metadata;
import ch.g_7.util.fdt.data.Request;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.exception.FDTException;
import ch.g_7.util.fdt.exception.StatusCode;
import ch.g_7.util.fdt.function.Reciever;

/**
 * Simple FDTServerListner
 * Waits until run is invoked, then processes the received data;
 * 
 * @author Joey Sciamanna
 */
public class FDTServerListner implements Function<byte[], byte[]>{

	private Map<String, Reciever> recievers;
	private Metadata metadata;

	public FDTServerListner() {
		recievers = new HashMap<String, Reciever>();
		metadata = new Metadata();
	}
	
	/**
	 * Method to handle incoming requests
	 * @param data the incoming data
	 * @return the response
	 */
	@Override
	public byte[] apply(byte[] data) {
		Response response = null;
		try {
			Request request = new Request(new String(data, StandardCharsets.UTF_8));
			Reciever reciever = recievers.get(request.getPath());
			if(reciever == null) {
				throw new FDTException("No function listening on path " + request.getPath() + " found", StatusCode.RECEIVER_NOT_FOUND);
			}
			response = reciever.recieve(request);
			response.setMetadata(metadata);
			
		}catch (FDTException e) {
			response = new Response(e.getStatusCode(), e.getMessage(), "");
			response.setMetadata(metadata);
		}
		
		return response.stringify().getBytes(StandardCharsets.UTF_8);
	}
	

	/**
	 * Adds an receiver to handle incoming requests 
	 * @param reciever The new receiver
	 */
	public void add(Reciever reciever) {
		recievers.put(reciever.getPath(), reciever);
	}

	/**
	 * Removes an receiver
	 * @param reciever The receiver to remove
	 */
	public void remove(Reciever reciever) {
		recievers.remove(reciever.getPath());
	}
}
