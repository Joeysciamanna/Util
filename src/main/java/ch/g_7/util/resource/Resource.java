package ch.g_7.util.resource;

public abstract class Resource implements IResource {

	@Override
	public final void init() {
		if(ResourceHandler.shallInitialize(this)) doInit();
	}
	
	protected abstract void doInit();
	
	@Override
	public final void close() {
		if(ResourceHandler.shallClose(this)) doClose();
	}

	protected abstract void doClose();
	
}
