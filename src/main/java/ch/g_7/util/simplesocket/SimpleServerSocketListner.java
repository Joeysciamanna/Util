package ch.g_7.util.simplesocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Function;

import ch.g_7.util.task.ActionChecker;
import ch.g_7.util.task.SecureRunner;

public class SimpleServerSocketListner extends ActionChecker {

	private ServerSocket serverSocket;
	private int port;
	private Function<byte[], byte[]> action;

	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;

	public SimpleServerSocketListner(int port, Function<byte[], byte[]> action) {
		this.port = port;
		this.action = action;
		setIntervall(200);
	}

	@Override
	protected void onStart() {
		serverSocket = new SecureRunner<>(() -> new ServerSocket(port)).get();
	}

	@Override
	protected void onClose() {
		new SecureRunner<>(() -> serverSocket.close()).get();
	}

	@Override
	protected void onAction() {
		try {
			socket = serverSocket.accept();
			
			//READING
			inputStream = new DataInputStream(socket.getInputStream());
			int dataLenght = inputStream.readInt();
			byte[] request = inputStream.readNBytes(dataLenght);
			
			byte[] response = action.apply(request);
			
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

	@Override
	protected boolean check() {
		return true;
	}

}
