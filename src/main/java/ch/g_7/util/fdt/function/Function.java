package ch.g_7.util.fdt.function;

/**
 * Superclass of Receiver / Sender, defines default methods.
 * 
 * @author Joey Sciamanna
 */
public abstract class Function {

	private String endpoint;
	
	public Function(String endpoint) {
		this.endpoint = endpoint;
	}
	
	public Function() {
		this("default");
	}
	
	/**
	 * Get the path / destination of this
	 * @return The path / destination
	 */
	public final String getPath() {
		return getName() + "/" + getEndpoint();
	}
	
	/**
	 * Get the name of this.
	 * This must be the same as the corresponding receiver / sender
	 * @return The name of this.
	 */
	protected abstract String getName();
	
	/**
	 * Get the end point of this.
	 * Can be used to register multiple Functions of the same type.
	 * This must be the same as the corresponding receiver / sender
	 * @return The end point of this.
	 */
	protected final String getEndpoint() {
		return endpoint;
	}
	
}
