package ch.g_7.util.fdt.exception;

import ch.g_7.util.fdt.data.Metadata;

public class ServerException extends FDTException{

	private static final long serialVersionUID = 5568624312761941864L;

	private Metadata metadata;
	
	public ServerException(String message, StatusCode statusCode) {
		super(message, statusCode);
	}

	public ServerException(Metadata metadata, String message, StatusCode statusCode) {
		super(message, statusCode);
		this.metadata = metadata;
	}

	public Metadata getMetadata() {
		return metadata;
	}
}
