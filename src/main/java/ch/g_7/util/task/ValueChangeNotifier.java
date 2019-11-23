package ch.g_7.util.task;

import java.util.ArrayList;
import java.util.List;

import ch.g_7.util.task.Task.SimpleTask;

public class ValueChangeNotifier<T> implements SimpleTask {

	
	private List<Task<T, Void>> listners;
	
	private T value;
	private boolean changed;
	
	
	public ValueChangeNotifier() {
		this.listners = new ArrayList<>();
	}
	
	
	@Override
	public void runSimple() {
		if(changed) {
			listners.forEach((l)->l.run(value));
			changed = false;
		}
	}

	public void valueChanged(T value) {
		this.value = value;
		this.changed  = true;
	}

	public void addListner(Task<T, Void> listner) {
		this.listners.add(listner);
	}
	
	public void removeListner(Task<T, Void> listner) {
		this.listners.remove(listner);
	}
	
	public void removeAll() {
		this.listners.clear();
	}
	
	public boolean isChanged() {
		return changed;
	}
}
