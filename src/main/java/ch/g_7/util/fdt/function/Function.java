package ch.g_7.util.fdt.function;


public abstract class Function {

	private String endpoint;
	
	public Function(String endpoint) {
		this.endpoint = endpoint;
	}
	
	public Function() {
		this("default");
	}
	
	
	public final String getPath() {
		return getName() + "/" + getEndpoint();
	}
	
	protected abstract String getName();
	
	protected final String getEndpoint() {
		return endpoint;
	}
	
}
