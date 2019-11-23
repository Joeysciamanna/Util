package ch.g_7.util.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import ch.g_7.util.task.Task.VoidTask;

public class TaskList<I> implements VoidTask<I> {

	private List<Task<I, Void>> tasks;

	public TaskList() {
		this.tasks = new ArrayList<Task<I, Void>>();
	}

	public void runAsync(I i) {
		CompletableFuture.runAsync(() -> {
			runVoid(i);
		});
	}

	@Override
	public void runVoid(I i) {
		tasks.forEach((t) -> t.run(null));
	}

	public void add(Task<I, Void> task) {
		tasks.add(task);
	}
	
	public void remove(Task<I, Void> task) {
		tasks.remove(task);
	}
}
