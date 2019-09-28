package ch.g_7.util.simplesocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import ch.g_7.util.stuff.SecureRunner;

public class SimpleSocketConnection implements IConnection{

	private Socket socket;
	private String domain;
	private int port;
	
	private SecureRunner<byte[], byte[]> sendingLogic;
	private DataOutputStream outputStream;
	private DataInputStream inputStream;
	
	
	public SimpleSocketConnection(int port, String domain) {
		this.port = port;
		this.domain = domain;
	}
	
	@Override
	public void close() {
		new SecureRunner<>(()->socket.close()).run();
	}

	@Override
	public void open() {
		socket = new SecureRunner<>(()->new Socket(domain, port)).run();
		
		sendingLogic = new SecureRunner<>((byte[] data)->{
			
			//WRITING
			outputStream = new DataOutputStream(socket.getOutputStream());
			outputStream.writeInt(data.length);
			outputStream.write(data);
			
			//READING
			inputStream = new DataInputStream(socket.getInputStream());
			int dataLenght = inputStream.readInt();
			byte[] response = inputStream.readNBytes(dataLenght);
			
	        return response;
		}).close(outputStream, inputStream);
	}

	@Override
	public byte[] send(byte[] data) {
		return sendingLogic.run(data);
	}


}
