package ch.g_7.util.simplesocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import ch.g_7.util.stuff.SecureRunner;
import ch.g_7.util.task.ActionChecker;
import ch.g_7.util.task.Task;

public class SimpleServerSocketListner extends ActionChecker {

	private ServerSocket serverSocket;
	private int port;
	private Task<byte[], byte[]> action;

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
	}

	@Override
	protected void onClose() {
		new SecureRunner<>(() -> serverSocket.close()).run();
	}

	@Override
	protected void onAction() {
		try {
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
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			new SecureRunner<>(()->null).close(outputStream,inputStream, socket).run();
		}
	}

	@Override
	protected boolean check() {
		return true;
	}

}
