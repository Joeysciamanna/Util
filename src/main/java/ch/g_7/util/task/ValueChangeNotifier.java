package ch.g_7.util.task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ValueChangeNotifier<T> {

	
	private List<Consumer<T>> listners;
	
	private T value;
	private boolean changed;
	
	
	public ValueChangeNotifier() {
		this.listners = new ArrayList<>();
	}
	
	
	public void run() {
		if(changed) {
			listners.forEach((l)->l.accept(value));
			changed = false;
		}
	}

	public void valueChanged(T value) {
		this.value = value;
		this.changed  = true;
	}

	public void addListner(Consumer<T> listner) {
		this.listners.add(listner);
	}
	
	public void removeListner(Consumer<T> listner) {
		this.listners.remove(listner);
	}
	
	public void removeAll() {
		this.listners.clear();
	}
	
	public boolean isChanged() {
		return changed;
	}
}
