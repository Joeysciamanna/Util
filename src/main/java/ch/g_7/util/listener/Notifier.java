package ch.g_7.util.listener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Notifier<T extends Event> {

	private final List<IListener<T>> listeners;
	private final Queue<T> events;
	
	public Notifier() {
		this.listeners = new ArrayList<>();
		this.events = new LinkedList<>();
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
			if (!event.isConsumed()){
				listner.handle(event);
			} else {
				return;
			}
		}
	}

	/**
	 * Adds the event to the event queue
	 * @param event
	 */
	public void register(T event) {
		events.add(event);
	}

	/**
	 * Clears the event queue, and adds the given event to it
	 */
	public void override(T event) {
		events.clear();
		register(event);
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
