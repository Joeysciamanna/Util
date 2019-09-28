package ch.g_7.util.parse.test;

import ch.g_7.util.fdt.FDTServer;
import ch.g_7.util.fdt.data.Metadata;
import ch.g_7.util.fdt.exception.FDTException;
import ch.g_7.util.fdt.function.string.StringReciever;

public class Test {

	public static void main(String[] args) throws Exception {
		FDTServer server = new FDTServer(1234);
		server.open();
		server.add(new StringReciever() {
			
			@Override
			public String recieveString(String data, Metadata metadata) throws FDTException {
				System.out.println(data);
				System.out.println(metadata.stringify());
				return "See you next time";
			}
		});
		
	}
}
