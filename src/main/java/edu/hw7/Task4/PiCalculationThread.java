package edu.hw7.Task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class PiCalculationThread extends Thread {
    private final AtomicInteger circleCount;
    private final int simulations;

    public PiCalculationThread(AtomicInteger circleCount, int simulations) {
        this.circleCount = circleCount;
        this.simulations = simulations;
    }

    @Override
    public void run() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        int currentCircleCount = 0;
        for (int i = 0; i < simulations; ++i) {
            double x = threadLocalRandom.nextDouble();
            double y = threadLocalRandom.nextDouble();
            if (x * x + y * y <= 1) {
                currentCircleCount++;
            }
        }
        circleCount.addAndGet(currentCircleCount);
    }
}
