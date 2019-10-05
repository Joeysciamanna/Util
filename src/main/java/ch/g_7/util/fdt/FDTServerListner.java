package ch.g_7.util.fdt;


import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import ch.g_7.util.fdt.data.Metadata;
import ch.g_7.util.fdt.data.Request;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.exception.FDTException;
import ch.g_7.util.fdt.exception.StatusCode;
import ch.g_7.util.fdt.function.Reciever;
import ch.g_7.util.simplesocket.SimpleServerSocketListner;
import ch.g_7.util.stuff.Passable;
import ch.g_7.util.task.Task;

/**
 * Simple FDTServerListner
 * Waits until run is invoked, then processes the received data;
 * 
 * @author Joey Sciamanna
 */
public class FDTServerListner implements Task<byte[], byte[]>{

	private Map<String, Reciever> recievers;
	private Metadata metadata;

	public FDTServerListner() {
		recievers = new HashMap<String, Reciever>();
		metadata = new Metadata();
	}
	
	@Override
	public byte[] run(byte[] data) {
		Response response = null;
		try {
			Request request = new Request(new String(data, StandardCharsets.UTF_8));
			Reciever reciever = recievers.get(request.getPath());
			if(reciever == null) {
				throw new FDTException("No function listening on path " + request.getPath() + " found", StatusCode.FUNCTION_NOT_FOUND);
			}
			response = reciever.recieve(request);
			
		}catch (FDTException e) {
			response = new Response(metadata, e.getStatusCode(), e.getMessage(), "");
		}
		
		return response.stringify().getBytes(StandardCharsets.UTF_8);
	}
	

	
	
	public void add(Reciever reciever) {
		recievers.put(reciever.getPath(), reciever);
	}

	public void remove(Reciever reciever) {
		recievers.remove(reciever.getPath());
	}
}
