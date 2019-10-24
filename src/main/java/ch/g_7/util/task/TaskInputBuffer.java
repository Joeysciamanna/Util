package ch.g_7.util.task;

import java.util.LinkedList;
import java.util.Queue;

import ch.g_7.util.task.Task.SimpleTask;

public class TaskInputBuffer<I> implements SimpleTask{

	private Queue<I> inputs;
	private Task<I, Void> task;
	
	public TaskInputBuffer(Task<I, Void> task) {
		this.task = task;
		inputs = new LinkedList<>();
	}
	
	
	@Override
	public void runSimple() {
		while (!inputs.isEmpty()) {
			task.run(inputs.poll());
		}
	}

	public void add(I input) {
		inputs.add(input);
	}
	
	public void clear() {
		inputs.clear();
	}



}
