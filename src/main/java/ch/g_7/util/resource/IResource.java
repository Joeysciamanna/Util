package ch.g_7.util.resource;

import ch.g_7.util.common.Closeable;
import ch.g_7.util.common.Initializable;

public interface IResource extends Initializable, Closeable{

	static final IResourceManager RESOURCE_MANAGER = ResourceManager.getInstance();
	
	/**
	 * Do not call this method.
	 * This method will be called internal.
	 */
	@Override
	@Deprecated
	void close();
	
	/**
	 * Do not call this method.
	 * This method will be called internal.
	 */
	@Override
	@Deprecated
	void init();

	
	int getResourceId();
	
	
	default void bind(IDepender depender) {
		RESOURCE_MANAGER.bind(depender, this);
	}
	
	default void unbind(IDepender depender) {
		RESOURCE_MANAGER.unbind(depender, this);
	}
}
