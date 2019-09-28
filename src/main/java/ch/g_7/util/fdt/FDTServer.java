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
import ch.g_7.util.process.Task;
import ch.g_7.util.simplesocket.SimpleServerSocketListner;
import ch.g_7.util.stuff.Passable;

public class FDTServer implements Passable{

	private Passable listner;
	private Map<String, Reciever> recievers;
	private Metadata metadata;
	
	public FDTServer(int port) {
		listner = new SimpleServerSocketListner(port, getAction());
		recievers = new HashMap<String, Reciever>();
	}
	
	public FDTServer(Passable listner) {
		recievers = new HashMap<String, Reciever>();
		this.listner = listner;
	}

	public Task<byte[], byte[]> getAction() {
		return (d)-> recive(d);
	}
	
	public byte[] recive(byte[] data) {
		Response response = null;
		try {
			Request request = new Request(new String(data, StandardCharsets.UTF_8));
			Reciever reciever = recievers.get(request.getPath());
			if(reciever == null) {
				throw new FDTException("No function listening on path " + request.getPath() + " found", StatusCode.FUNCTION_NOT_FOUND);
			}
			String responseData = reciever.recieve(request);
			response = new Response(metadata, StatusCode.SUCCESS, "", responseData);
			
		}catch (FDTException e) {
			response = new Response(metadata, e.getStatusCode(), e.getMessage(), "");
		}
		
		return response.stringify().getBytes(StandardCharsets.UTF_8);
	}
	

	@Override
	public void close() throws Exception {
		listner.close();
	}
	
	@Override
	public void open() throws Exception {
		listner.open();
	}
	
	
	public void add(Reciever reciever) {
		recievers.put(reciever.getPath(), reciever);
	}
}
