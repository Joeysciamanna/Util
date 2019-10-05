package ch.g_7.util.task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TaskQueue<I, O> implements Task<I, List<O>> {
	
	private Queue<Task<I, O>> tasks;
	
	public TaskQueue() {
		tasks = new LinkedList<Task<I, O>>();
	}

	public void add(Task<I, O> task) {
		tasks.add(task);
	}

	public Task<I, O> pullProcessor() {
		return tasks.poll();
	}

	public Queue<Task<I, O>> getProcessors() {
		return tasks;
	}

	public void clearProcessors() {
		tasks.clear();
	}

	@Override
	public List<O> run(I t) {
		ArrayList<O> values = new ArrayList<O>();
		while (!tasks.isEmpty()) {
			values.add(pullProcessor().run(t));
		}
		return values;
	}

}
