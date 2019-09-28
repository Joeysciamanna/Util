package ch.g_7.util.fdt.function.string;

import ch.g_7.util.fdt.FDTConnection;
import ch.g_7.util.fdt.function.Sender;

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

	
}
