package ch.g_7.util.task;

import java.util.LinkedList;
import java.util.Queue;

public class TaskInputBuffer<I> implements Task<Void, Void>{

	private Queue<I> inputs;
	private Task<I, Void> task;
	
	public TaskInputBuffer(Task<I, Void> task) {
		this.task = task;
		inputs = new LinkedList<>();
	}
	
	
	@Override
	public Void run(Void v) {
		while (!inputs.isEmpty()) {
			task.run(inputs.poll());
		}
		return v;
	}

	public void add(I input) {
		inputs.add(input);
	}
	
	public void clear() {
		inputs.clear();
	}
}
