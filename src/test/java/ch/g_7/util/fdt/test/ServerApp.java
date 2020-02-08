package ch.g_7.util.fdt.test;

import java.net.InetAddress;

import ch.g_7.util.fdt.FDTServerListner;
import ch.g_7.util.fdt.data.Metadata;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.exception.FDTException;
import ch.g_7.util.fdt.exception.StatusCode;
import ch.g_7.util.fdt.function.string.StringReceiver;
import ch.g_7.util.simplesocket.SimpleServerSocketListner;


public class ServerApp {


	public static void main(String[] args) throws Exception {
		System.out.println(InetAddress.getLocalHost());
		FDTServerListner fdtServerListner = new FDTServerListner();
		SimpleServerSocketListner serverSocketListner = new SimpleServerSocketListner(4004, fdtServerListner);
		serverSocketListner.start();
		fdtServerListner.add(new StringReceiver() {
			
			@Override
			public Response recieveString(String data, Metadata metadata) throws FDTException {
				System.out.println("Request: " + data);
				System.out.println("Metadata: " + metadata.stringify());

				return new Response(StatusCode.SUCCESS, "", "See you next time");
			}
		});
		

		
//		serverSocketListner.close();

	}
	
}
