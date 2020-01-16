package ch.g_7.util.resource;

import java.util.List;

public interface IResourceManager {
	
	boolean hasDependency(IDepender depender, IResource resource);
	
	boolean hasDependencies(IDepender depender);
	
	void bind(IDepender depender, IResource resource);
	
	void unbind(IDepender depender, IResource resource);
	
	void unbindAll(IDepender depender);
	
	boolean isUsed(IResource resource);
	
	List<IDepender> getDependers(IResource resource);
	
	List<IResource> getDependencies(IDepender depender);
	
	boolean hasUnclosedResources();
	
	String getUnclosedResources();
}
