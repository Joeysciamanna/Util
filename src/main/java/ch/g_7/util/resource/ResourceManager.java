package ch.g_7.util.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ResourceManager implements IResourceManager {

	private static final ResourceManager instance = new ResourceManager();

	private Map<IResource, List<IDepender>> dependencies;

	private static int resourceIdCounter = 0;

	public static int createResourceId(){
		return ++resourceIdCounter;
	}


	private ResourceManager() {
		this.dependencies = new HashMap<IResource, List<IDepender>>();
	}

	public static IResourceManager getInstance() {
		return instance;
	}

	@Override
	@SuppressWarnings("deprecation")
	public void bind(IDepender depender, IResource resource) {
		if (!hasDependency(depender, resource)) {
			if(dependencies.containsKey(resource)) {
				dependencies.get(resource).add(depender);
			} else {
				List<IDepender> dependers = new ArrayList<IDepender>();
				dependers.add(depender);
				dependencies.put(resource, dependers);
				resource.init();
			}
		}
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void unbind(IDepender depender, IResource resource) {
		if (hasDependency(depender, resource)) {
			List<IDepender> dependers = dependencies.get(resource);
			dependers.remove(depender);
			if(dependers.isEmpty()) {
				resource.close();
				dependencies.remove(resource);
			}
		} else {
			throw new IllegalStateException("There is no closable dependency betwen resourec [" + resource+ "] and depender [" + depender + "]");
		}
	}

	@Override
	public void unbindAll(IDepender depender) {
		for (IResource resource : getDependencies(depender)) {
			unbind(depender, resource);
		}
	}

	@Override
	public boolean hasDependency(IDepender depender, IResource resource) {
		return getDependencies(depender).contains(resource);
	}

	@Override
	public boolean hasDependencies(IDepender depender) {
		return !getDependencies(depender).isEmpty();
	}

	@Override
	public boolean isUsed(IResource resource) {
		return !getDependers(resource).isEmpty();
	}

	@Override
	public List<IDepender> getDependers(IResource resource) {
		return dependencies.containsKey(resource) ? dependencies.get(resource) : new ArrayList<IDepender>();
	}

	@Override
	public List<IResource> getDependencies(IDepender depender) {
		List<IResource> resources = new ArrayList<IResource>();
		for(Entry<IResource, List<IDepender>> entry : dependencies.entrySet()) {
			if(entry.getValue().contains(depender)) {
				resources.add(entry.getKey());
			}
		}
		return resources;
	}

	@Override
	public boolean hasUnclosedResources() {
		return !dependencies.isEmpty();
	}
	
	@Override
	public String getUnclosedResources() {
		StringBuilder builder = new StringBuilder();
		for(Entry<IResource, List<IDepender>> entry : dependencies.entrySet()) {
			builder.append(entry.getValue().size()).append(" x Resource [").append(entry.getKey().getResourceId()).append("]:\n");
			for(IDepender depender : entry.getValue()) {
				builder.append("\t").append(depender).append("\n");
			}
		}
		return builder.toString();
	}
	
	@Override
	public int getCurrentResourceCount() {
		return dependencies.size();
	}
	
	@Override
	public int getCurrentResourceAllocations() {
		return resourceIdCounter;
	}
	
}
