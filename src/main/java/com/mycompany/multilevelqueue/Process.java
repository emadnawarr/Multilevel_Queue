package com.mycompany.multilevelqueue;

public class Process {

    private String ID;
    private ProcessState state;
    private int timeRemaining;

    public Process(String ID, ProcessState state, int timeRemaining) {
        this.ID = ID;
        this.state = state;
        this.timeRemaining = timeRemaining;
    }

    public void processCompleted(Process process) {
        process.setState(ProcessState.COMPLETED);
        process.setTimeRemaining(0);
    }

    public void updateTimeRemaining(int timeCompleted) {
        this.timeRemaining = this.timeRemaining - timeCompleted;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public void printProcess() {
        System.out.println(this.getID() + "  " + this.getState() + "  " + this.getTimeRemaining());
    }

    @Override
    public String toString() {
        return this.getID() + "  " + this.getState() + "  " + this.getTimeRemaining();
    }

}
