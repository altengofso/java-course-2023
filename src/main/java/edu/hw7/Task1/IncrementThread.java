package edu.hw7.Task1;

public class IncrementThread extends Thread {
    private final int incrementCount;
    private final Counter counter;

    public IncrementThread(Counter counter, int incrementCount) {
        this.counter = counter;
        this.incrementCount = incrementCount;

    }

    @Override
    public void run() {
        for (int i = 0; i < incrementCount; ++i) {
            counter.increment();
        }
    }
}
