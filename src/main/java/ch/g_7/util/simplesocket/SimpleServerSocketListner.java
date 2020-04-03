package ch.g_7.util.simplesocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import ch.g_7.util.loop.Loop;
import ch.g_7.util.task.SecureRunner;

public class SimpleServerSocketListner extends Loop {

	private ServerSocket serverSocket;
	private int port;
	private IServer server;

	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;

	public SimpleServerSocketListner(int port, IServer server) {
		this.port = port;
		this.server = server;
	}

	@Override
	protected void onStart() {
		serverSocket = new SecureRunner<>(() -> new ServerSocket(port)).get();
	}

	@Override
	protected void onStop() {
		new SecureRunner<>(() -> serverSocket.close()).get();
	}

	@Override
	protected void run(float deltaMillis) {
		try {
			socket = serverSocket.accept();
			
			//READING
			inputStream = new DataInputStream(socket.getInputStream());
			int dataLenght = inputStream.readInt();
			byte[] request = inputStream.readNBytes(dataLenght);
			
			byte[] response = server.receive(request);
			
			//WRITING
			outputStream = new DataOutputStream(socket.getOutputStream());
			outputStream.writeInt(response.length);
			outputStream.write(response);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			new SecureRunner<>(()->null).close(outputStream,inputStream, socket).get();
		}
	}


}
