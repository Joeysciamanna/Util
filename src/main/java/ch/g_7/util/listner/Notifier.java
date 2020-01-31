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
	
	public void reportAll() {
		while (!events.isEmpty()) {
			T event = events.poll();
			for (IListner<T> listner : listners) {
				listner.onAction(event);
			}
		}
	}
	
	public void reportLatest() {
		T event = events.poll();
		for (IListner<T> listner : listners) {
			listner.onAction(event);
		}
		events.clear();
	}
	
	/**
	 * Adds the given Event to the event queue
	 * @param event
	 */
	public void addEvent(T event) {
		events.add(event);
	}
	
	public void addAndNotify(T event) {
		addEvent(event);
		reportAll();
	}
	
	/**
	 * Clears the event queue, and adds the given event to it
	 */
	public void putEvent(T event) {
		events.clear();
		addEvent(event);
	}
	
	public void putEventAndNotifiy(T event) { 
		events.clear();
		addAndNotify(event);
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
