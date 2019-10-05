package ch.g_7.util.task;

import java.util.ArrayList;
import java.util.List;

public class TaskCallBuffer<I> implements Task<Void,Void> {

    private List<I> inputData;
    private Task<I, Void> task;

    private long callBuffer;
    private long lastCall;

    public TaskCallBuffer(Task<I,Void> task, long callBuffer) {
        this.callBuffer = callBuffer;
        this.task = task;
        this.inputData = new ArrayList<I>();
    }

    @Override
    public synchronized Void run(Void v) {
        for(I data : inputData){
            task.run(data);
        }
        inputData.clear();
        lastCall =  System.currentTimeMillis();
        return null;
    }


    public long getLastCall() {
        return lastCall;
    }

    public boolean isFull(){
        return callBuffer <= inputData.size();
    }

    public boolean isEmpty(){
        return inputData.isEmpty();
    }

    public synchronized void add(I data){
        inputData.add(data);
    }
}
