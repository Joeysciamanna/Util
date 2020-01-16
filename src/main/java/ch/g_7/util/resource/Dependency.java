package ch.g_7.util.resource;

import java.util.ArrayList;
import java.util.List;

public class Dependency implements IResource {
	
	private List<Object> dependers;
	private IResource resource;
	
	private boolean initialized;
	private boolean closed;
	
	public Dependency(IResource resource) {
		this.resource = resource;
		this.dependers = new ArrayList<Object>();
	}
	
	public void removeDepender(Object depender) {
		dependers.remove(depender);
	}
	
	public void addDepener(Object depender) {
		if(!dependers.contains(depender)) {
			dependers.add(depender);
		}
	}
	
	public boolean dependencyEquals(IResource resource) {
		return resource.getResourceId() == this.resource.getResourceId();
	}
	
	public boolean isUnused() {
		return dependers.isEmpty();
	}

	@Override
	public void init() {
		if(initialized)
			resource.init();
		else
			throw new IllegalStateException("Resource ["+resource+"] alredy initialized");
	}

	@Override
	public void close() {
		if(closed)
			resource.close();
		else
			throw new IllegalStateException("Resource ["+resource+"] alredy closed");
	}
	
	public int getResourceId() {
		return resource.getResourceId();
	}
}
