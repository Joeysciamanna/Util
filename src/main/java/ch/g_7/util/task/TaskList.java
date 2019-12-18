package ch.g_7.util.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class TaskList<T> {

	private List<Consumer<T>> tasks;

	public TaskList() {
		this.tasks = new ArrayList<Consumer<T>>();
	}

	public void runAsync(T i) {
		CompletableFuture.runAsync(() -> {
			run(i);
		});
	}
	
	public void run(T i) {
		tasks.forEach((t) -> t.accept(i));
	}

	public void add(Consumer<T> task) {
		tasks.add(task);
	}
	
	public void remove(Consumer<T> task) {
		tasks.remove(task);
	}
	
	public void clear() {
		tasks.clear();
	}
	
	public boolean isEmpty() {
		return tasks.isEmpty();
	}
}
