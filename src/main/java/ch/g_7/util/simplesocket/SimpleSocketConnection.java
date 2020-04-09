package ch.g_7.util.simplesocket;

import ch.g_7.util.helper.Util;
import ch.g_7.util.task.checked.Checked;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SimpleSocketConnection implements IConnection{

	private Socket socket;
	private String domain;
	private int port;
	
	private DataOutputStream outputStream;
	private DataInputStream inputStream;
	
	
	public SimpleSocketConnection(int port, String domain) {
		this.port = port;
		this.domain = domain;
	}
	
	@Override
	public void close() {
		Checked.run(socket::close);
	}

	@Override
	public void open() {
		socket = Checked.get(()->new Socket(domain, port));
	}

	@Override
	public byte[] send(byte[] data) {
		try {
			//WRITING
			outputStream = new DataOutputStream(socket.getOutputStream());
			outputStream.writeInt(data.length);
			outputStream.write(data);
			
			//READING
			inputStream = new DataInputStream(socket.getInputStream());
			int dataLenght = inputStream.readInt();
			byte[] response = inputStream.readNBytes(dataLenght);
	        return response;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			Util.close(inputStream, outputStream);
		}
	}


}
