package com.mycompany.multilevelqueue;

public class FCFS extends MyQueue {

    public FCFS(int size) {
        super(size);
    }

    @Override
    public Process update() {
        Process process = this.getHeadProcess();
        process.processCompleted(process);
        this.removeProcess(process);
        return process;
    }
}
