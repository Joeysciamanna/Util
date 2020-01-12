package ch.g_7.util.task;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class TaskInputQueue<T> {

	private Queue<T> inputs;
	private Consumer<T> task;
	
	public TaskInputQueue(Consumer<T> task) {
		this.task = task;
		inputs = new LinkedList<>();
	}
	
	public synchronized void run() {
		while (!inputs.isEmpty()) {
			task.accept(inputs.poll());
		}
	}

	public synchronized void runAsync() {
		CompletableFuture.runAsync(() -> {
			run();
		});
	}
	
	public synchronized void add(T input) {
		inputs.add(input);
	}
	
	public synchronized void clear() {
		inputs.clear();
	}



}
