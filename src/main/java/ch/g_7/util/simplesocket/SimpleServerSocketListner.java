package ch.g_7.util.simplesocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import ch.g_7.util.process.Task;
import ch.g_7.util.stuff.ActionChecker;
import ch.g_7.util.stuff.SecureRunner;

public class SimpleServerSocketListner extends ActionChecker {

	private ServerSocket serverSocket;
	private int port;
	private Task<byte[], byte[]> action;


	private SecureRunner<Void, Void> handlingLogic;
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;

	public SimpleServerSocketListner(int port, Task<byte[], byte[]> action) {
		this.port = port;
		this.action = action;
		setIntervall(200);
	}

	@Override
	protected void onStart() {
		serverSocket = new SecureRunner<>(() -> new ServerSocket(port)).run();
		
		handlingLogic = new SecureRunner<Void, Void>(() -> {
			
			socket = serverSocket.accept();
			
			//READING
			inputStream = new DataInputStream(socket.getInputStream());
			int dataLenght = inputStream.readInt();
			byte[] request = inputStream.readNBytes(dataLenght);
			
			byte[] response = action.run(request);
			
			//WRITING
			outputStream = new DataOutputStream(socket.getOutputStream());
			outputStream.writeInt(response.length);
			outputStream.write(response);
			
		}).close(socket, inputStream, outputStream);
	}

	@Override
	protected void onClose() {
		new SecureRunner<>(() -> serverSocket.close()).run();
	}

	@Override
	protected void onAction() {
		handlingLogic.run();
	}

	@Override
	protected boolean check() {
		return true;
	}

}
