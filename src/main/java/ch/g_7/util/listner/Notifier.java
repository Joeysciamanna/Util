package ch.g_7.util.listner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Notifier<T extends Event> {

	private List<IListener<T>> listeners;
	private Queue<T> events;
	
	public Notifier() {
		listeners = new ArrayList<>();
		events = new LinkedList<>();
	}
	
	public void reportAll() {
		while (!events.isEmpty()) {
			report(events.poll());
		}
	}
	
	public void reportLatest() {
		report(events.poll());
		events.clear();
	}

	public void report(T event) {
		for (IListener<T> listner : new ArrayList<>(listeners)) {
			while (!event.isConsumed()){
				listner.onAction(event);
			}
		}
	}

	/**
	 * Adds the event to the event queue
	 * @param event
	 */
	public void addToQueue(T event) {
		events.add(event);
	}

	/**
	 * Clears the event queue, and adds the given event to it
	 */
	public void overrideQueue(T event) {
		events.clear();
		addToQueue(event);
	}

	public void addListner(IListener<T> listner) {
		listeners.add(listner);
	}
	
	public void removeListner(IListener<T> listner) {
		listeners.remove(listner);
	}
	
	public void clear() {
		listeners.clear();
	}
}
