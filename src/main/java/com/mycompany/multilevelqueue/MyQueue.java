package com.mycompany.multilevelqueue;

import java.util.LinkedList;
import java.util.Queue;


public abstract class MyQueue {
    
    protected int size;
    protected Queue<Process> processes;

    public MyQueue(int size) {
        this.processes = new LinkedList<>();
        this.size = size;
    }
    public abstract Process update();
    
    public boolean isFull(){
        return this.processes.size() >= this.size;
    }
    public boolean isEmpty(){
        return this.processes.isEmpty();
    }

    public void addProcess(Process process) {
        this.processes.add(process);
    }

    public void removeProcess(Process process) {
        this.processes.remove(process);
    }
    
    public Process getHeadProcess(){
        return this.processes.element();
    }
    
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Queue<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(Queue<Process> processes) {
        this.processes = processes;
    }

}
