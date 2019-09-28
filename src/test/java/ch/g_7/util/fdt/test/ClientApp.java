package ch.g_7.util.fdt.test;

import ch.g_7.util.fdt.FDTConnection;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.function.string.StringSender;
import ch.g_7.util.simplesocket.SimpleSocketConnection;

public class ClientApp {


	public static void main(String[] args) {
		FDTConnection connection = new FDTConnection(new SimpleSocketConnection(1234, "192.168.181.1"));
		StringSender stringSender = new StringSender(connection);
		Response response = stringSender.sendString("hello mister");
		System.out.println("Response: " + response.getData());
		System.out.println("Metadata: " + response.getMetadata().stringify());
	}
}
