package ch.g_7.util.resource;

public abstract class Resource implements IResource {

	private static int resourceIdCounter;
	private final int resourceId;
	
	public Resource() {
		this.resourceId = ++resourceIdCounter;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Resource ? ((Resource)obj).resourceId == resourceId : false;
	}
	
	@Override
	public int getResourceId() {
		return resourceId;
	}
	
	protected void init(IResource resource) {
		ResourceHandler.getInstance().addDepender(this, resource);
	}
	
	
	public void close(IResource resource) {
		ResourceHandler.getInstance().removeDependency(this, resource);
	}
	
	@Override
	public void close() {
		ResourceHandler.getInstance().removeDepender(this);
	}
}
