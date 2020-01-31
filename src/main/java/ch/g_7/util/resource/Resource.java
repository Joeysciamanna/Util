package ch.g_7.util.resource;

public abstract class Resource implements IResource, IDepender {

	private static final IResourceManager RESOURCE_MANAGER = ResourceManager.getInstance();
	
	private static int resourceIdCounter;
	private final int resourceId;
	
	private boolean initialized, closed;
	
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
	
	@Override
	@Deprecated
	public final void init() {
		if(initialized) {
			throw new IllegalAccessError("Cant init resource [" + getResourceId() + "], resource alredy initialized");
		}
		doInit();
		initialized = true;
	}

	@Override
	@Deprecated
	public final void close() {
		if(closed || RESOURCE_MANAGER.isUsed(this)) {
			throw new IllegalAccessError("Cant close resource [" + getResourceId() + "], resource alredy closed or still in use");
		}
		doClose();
		closed = true;
	}
	
	protected abstract void doInit();
	
	protected abstract void doClose();
	
	protected void bindTo(IResource... resources) {
		for (IResource resource : resources) {
			if(resource != null) {
				resource.bind(this);
			}
		}
	}
	
	protected void unbindForm(IResource... resources) {
		for (IResource resource : resources) {
			if(resource != null) {
				resource.unbind(this);
			}
		}
	}
	
	public static int getResourceIdCounter() {
		return resourceIdCounter;
	}
}

