package ch.g_7.util.resource;

public abstract class Resource implements IResource {

	private static int resourceIdCounter;
	private final int resourceId;
	
	public Resource() {
		this.resourceId = ++resourceIdCounter;
	}
	
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
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Resource ? ((Resource)obj).resourceId == resourceId : false;
	}
	
	@Override
	public int getResourceId() {
		return resourceId;
	}
}
