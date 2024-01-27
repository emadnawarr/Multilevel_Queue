package com.mycompany.multilevelqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class QueueManager {

    private final int NUM_OF_PROCESSES = 100;
    private final int sizeFirstQueue = 10;
    private final int sizeSecondQueue = 20;
    private final int sizeThirdQueue = 30;
    private final int quantumFirstQueue = 8;
    private final int quantumSecondQueue = 16;
    private final int cpu_utilizationFirstQueue = 50;
    private final int cpu_utilizationSecondQueue = 30;
    private final int cpu_utilizationThirdQueue = 20;

    private final MyQueue firstQueue;
    private final MyQueue secondQueue;
    private final MyQueue thirdQueue;
    private final Queue<Process> pendingQueue1;
    private final Queue<Process> pendingQueue2;
    private final Queue<Process> pendingQueue3;
    private final Queue<Process> completedQueue;
    private final Process[] readyProcess;

    public QueueManager() {
        this.firstQueue = new RoundRobin(sizeFirstQueue, quantumFirstQueue);
        this.secondQueue = new RoundRobin(sizeSecondQueue, quantumSecondQueue);
        this.thirdQueue = new FCFS(sizeThirdQueue);
        this.readyProcess = processFactory();
        this.pendingQueue1 = new LinkedList<>();
        this.pendingQueue2 = new LinkedList<>();
        this.pendingQueue3 = new LinkedList<>();
        this.completedQueue = new LinkedList<>();
    }

    public void init() {
        int randomNum;
        initProcesses();
        while (!isComplete()) {
            loadProcess(this.pendingQueue1, this.firstQueue);
            loadProcess(this.pendingQueue2, this.secondQueue);
            loadProcess(this.pendingQueue3, this.thirdQueue);
            randomNum = getRandomNum(100);
            Process process;
            if (randomNum < cpu_utilizationFirstQueue && !this.firstQueue.isEmpty()) {
                process = this.firstQueue.update();
                if (process.getState() == ProcessState.COMPLETED) {
                    process.printProcess();
                    this.completedQueue.add(process);
                } else {
                    this.pendingQueue2.add(process);
                }
            } else if (randomNum < (cpu_utilizationFirstQueue + cpu_utilizationSecondQueue) && !this.secondQueue.isEmpty()) {
                process = this.secondQueue.update();
                if (process.getState() == ProcessState.COMPLETED) {
                    process.printProcess();
                    this.completedQueue.add(process);
                } else {
                    randomNum = getRandomNum(100);
                    if (randomNum < 50) {
                        this.pendingQueue1.add(process);
                    } else {
                        this.pendingQueue3.add(process);
                    }
                }
            } else if (randomNum < (cpu_utilizationFirstQueue + cpu_utilizationSecondQueue + cpu_utilizationThirdQueue) && !this.thirdQueue.isEmpty()) {
                process = this.thirdQueue.update();
                process.printProcess();
                this.completedQueue.add(process);

            }
        }
    }

    public void loadProcess(Queue pendingQueue, MyQueue queue) {
        if (!pendingQueue.isEmpty() && !queue.isFull()) {
            queue.addProcess((Process) pendingQueue.poll());
        }
    }

    public boolean isComplete() {
        return this.firstQueue.isEmpty() && this.secondQueue.isEmpty() && this.thirdQueue.isEmpty() && this.pendingQueue1.isEmpty() && this.pendingQueue2.isEmpty() && this.pendingQueue3.isEmpty();
    }

    public void initProcesses() {
        for (int i = 0; i < 100; i++) {
            this.pendingQueue1.add(this.readyProcess[i]);
        }
    }

    private Process[] processFactory() {
        int burstTime;
        Process[] newProcesses = new Process[NUM_OF_PROCESSES];
        for (int i = 0; i < NUM_OF_PROCESSES; i++) {
            burstTime = getRandomNum(100);
            newProcesses[i] = new Process(Integer.toString(i + 1), ProcessState.READY, burstTime);
        }
        return newProcesses;
    }

    private int getRandomNum(int max) {
        Random random = new Random();
        int randomNum = random.nextInt(max);
        while (randomNum == 0) {
            randomNum = random.nextInt(max);
        }
        return randomNum;
    }

    public void printInitialProcesses() {
        for (Process p : this.readyProcess) {
            p.printProcess();
        }
        System.out.println(" ");
    }

}
