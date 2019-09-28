package ch.g_7.util.fdt.test;

import ch.g_7.util.fdt.FDTConnection;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.function.string.StringSender;
import ch.g_7.util.simplesocket.SimpleSocketConnection;

public class ClientApp {


	public static void main(String[] args) throws Exception {
		//192.168.1.155
		FDTConnection connection = new FDTConnection(new SimpleSocketConnection(4004, "localhost"));
		connection.open();
		StringSender stringSender = new StringSender(connection);
		Response response = stringSender.sendString("hello mister");
		System.out.println("Response: " + response.getData());
		System.out.println("Metadata: " + response.getMetadata().stringify());
		
		connection.close();
	}
}
