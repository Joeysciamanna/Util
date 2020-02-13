package ch.g_7.util.resource;

import ch.g_7.util.common.Closeable;
import ch.g_7.util.common.Initializable;

public interface IResource extends Initializable, Closeable, IDepender{

	static final IResourceManager RESOURCE_MANAGER = ResourceManager.getInstance();
	
	/**
	 * Do not call this method.
	 * This method should be called internal.
	 */
	@Override
	@Deprecated
	void close();
	
	/**
	 * Do not call this method.
	 * This method should be called internal.
	 */
	@Override
	@Deprecated
	void init();

	default void bind(IDepender depender) {
		if(depender.getResourceId() == getResourceId()){
			throw new IllegalArgumentException("Can't bind ["+this+"] to itself");
		}
		RESOURCE_MANAGER.bind(depender, this);
	}
	
	default void unbind(IDepender depender) {
		if(depender.getResourceId() == getResourceId()){
			throw new IllegalArgumentException("Can't unbind ["+this+"] to itself");
		}
		RESOURCE_MANAGER.unbind(depender, this);
	}
}
