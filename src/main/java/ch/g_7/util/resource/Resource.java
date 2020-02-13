package ch.g_7.util.resource;

import java.util.Objects;

public abstract class Resource implements IResource, IDepender {

	private static final IResourceManager RESOURCE_MANAGER = ResourceManager.getInstance();
	
	private static int resourceIdCounter;
	private final int resourceId;
	
	private boolean initialized, closed;
	
	public Resource() {
		this.resourceId = ++resourceIdCounter;
	}

	@Override
	@Deprecated
	public final void init() {
		if(initialized) {
			throw new IllegalAccessError("Cant init resource [" + this + "], resource alredy initialized");
		}
		doInit();
		initialized = true;
		closed = false;
	}

	@Override
	@Deprecated
	public final void close() {
		if(closed || RESOURCE_MANAGER.isUsed(this)) {
			throw new IllegalAccessError("Cant close resource [" + this + "], resource alredy closed or still in use");
		}
		doClose();
		closed = true;
		initialized = false;
	}

	@Override
	public final void bind(IDepender depender) {
		IResource.super.bind(depender);
	}

	@Override
	public void unbind(IDepender depender) {
		IResource.super.unbind(depender);
	}

	protected abstract void doInit();
	
	protected abstract void doClose();
	
	public static int getResourceIdCounter() {
		return resourceIdCounter;
	}

	@Override
	public int hashCode() {
		return Objects.hash(resourceId);
	}

	@Override
	public int getResourceId() {
		return resourceId;
	}
}

