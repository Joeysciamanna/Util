package ch.g_7.util.resource;

import java.util.ArrayList;
import java.util.List;

public final class ResourceHandler {


	private List<Dependency> dependencies;
	
	private ResourceHandler() {
		this.dependencies = new ArrayList<Dependency>();
	}
	
	public void removeDepender(Object depender) {
		for (Dependency dependency : dependencies) {
			dependency.removeDepender(depender);
			if(dependency.isUnused()) {
				dependency.close();
				dependencies.remove(dependency);
				return;
			}
		}
	}
	
	public void addDepender(Object depender, IResource resource) {
		for (Dependency d : dependencies) {
			if(d.dependencyEquals(resource)) {
				d.addDepener(depender);
				return;
			}
		}
		Dependency dependency = new Dependency(resource);
		dependency.addDepener(depender);
		dependency.init();
		dependencies.add(dependency);
	}
	
	public boolean hasUnclosedResources() {
		return !dependencies.isEmpty();
	}

	public String getUnclosedResourcesTable() {
		StringBuilder stringBuilder = new StringBuilder("Unclosed Resources:\n");
		for (Dependency dependency : dependencies) {
			stringBuilder.append(dependency.getResourceId()).append(", ");
		}
		return stringBuilder.toString();
	}
	
	@Deprecated
	public void closeAll() {
		dependencies.forEach((d) -> {
			d.close();
		});
	}

}
