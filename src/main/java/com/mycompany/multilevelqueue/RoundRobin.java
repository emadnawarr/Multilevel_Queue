package com.mycompany.multilevelqueue;

public class RoundRobin extends MyQueue {

    private final int quantum;

    public RoundRobin(int size, int quantum) {
        super(size);
        this.quantum = quantum;
    }

    @Override
    public Process update() {
        Process process = this.getHeadProcess();
        if (quantum >= process.getTimeRemaining()) {
            process.processCompleted(process);
            this.removeProcess(process);
        } else {
            process.updateTimeRemaining(quantum);
            this.removeProcess(process);
        }
        return process;
    }
}
