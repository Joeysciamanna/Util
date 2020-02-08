package ch.g_7.util.fdt.function.string;

import java.util.function.Consumer;

import ch.g_7.util.fdt.FDTConnection;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.exception.ServerException;
import ch.g_7.util.fdt.function.Sender;

/**
 * Sender for Strings, will send the Strings to the StringReceiver.
 * 
 * @author Joey Sciamanna
 */
public class StringSender extends Sender{

	public StringSender(FDTConnection connection, String endpoint) {
		super(connection, endpoint);
	}

	public StringSender(FDTConnection connection) {
		super(connection);
	}

	@Override
	public String getName() {
		return "string";
	}


	public final Response sendString(String data) throws ServerException {
		return send(data);
	}

	public final void sendStringAsync(String data, Consumer<Response> consumer) throws ServerException {
		sendAsync(data, consumer);
	}
}
