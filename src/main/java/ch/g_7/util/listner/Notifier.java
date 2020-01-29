package ch.g_7.util.listner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Notifier<T extends IEvent> {

	private List<IListner<T>> listners;
	private Queue<T> events;
	
	public Notifier() {
		listners = new ArrayList<>();
		events = new LinkedList<>();
	}
	
	public void notifyListners() {
		while (!events.isEmpty()) {
			T event = events.poll();
			for (IListner<T> listner : listners) {
				listner.onAction(event);
			}
			
		}
	}
	
	public void addEvent(T event) {
		events.add(event);
	}
	
	public void addAndNotify(T event) {
		addEvent(event);
		notifyListners();
	}
	
	public void addListner(IListner<T> listner) {
		listners.add(listner);
	}
	
	public void removeListner(IListner<T> listner) {
		listners.remove(listner);
	}
	
	public void clear() {
		listners.clear();
	}
}
