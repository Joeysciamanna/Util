package ch.g_7.util.task;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class TaskQueue<T> {
	
	private Queue<Consumer<T>> tasks;
	
	public TaskQueue() {
		tasks = new LinkedList<Consumer<T>>();
	}

	public void add(Consumer<T> task) {
		tasks.add(task);
	}

	public Consumer<T> pullProcessor() {
		return tasks.poll();
	}

	public Queue<Consumer<T>> getProcessors() {
		return tasks;
	}

	public void clearProcessors() {
		tasks.clear();
	}

	public void runAsync(T i) {
		CompletableFuture.runAsync(() -> {
			run(i);
		});
	}
	
	public void run(T t) {
		while (!tasks.isEmpty()) {
			pullProcessor().accept(t);
		}
	}

}
