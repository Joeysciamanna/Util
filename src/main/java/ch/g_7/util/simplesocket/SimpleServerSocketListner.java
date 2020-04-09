package ch.g_7.util.simplesocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import ch.g_7.util.helper.Util;
import ch.g_7.util.task.checked.Checked;
import ch.g_7.util.loop.Loop;

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
		serverSocket = Checked.get(()->new ServerSocket(port));
	}

	@Override
	protected void onStop() {
		Checked.run(serverSocket::close);
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
			Util.close(outputStream, inputStream, socket);
		}
	}


}
