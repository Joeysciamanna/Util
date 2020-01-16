package ch.g_7.util.resource;

import ch.g_7.util.common.Closeable;
import ch.g_7.util.common.Initializable;

public interface IResource extends Initializable, Closeable{

	
	/**
	 * Do not call this method.
	 * This method will be called internal.
	 */
	@Override
	void close();
	
	/**
	 * Do not call this method.
	 * This method will be called internal.
	 */
	@Override
	void init();

	
	int getResourceId();
}
