package edu.hw7.Task4;

import java.util.concurrent.atomic.AtomicInteger;

public final class MultiThreadPiCalculation {
    private MultiThreadPiCalculation() {
    }

    public static double calculatePI(int simulations, int threadsNumber) {
        AtomicInteger circleCount = new AtomicInteger(0);
        PiCalculationThread[] threads = new PiCalculationThread[threadsNumber];
        for (int i = 0; i < threadsNumber; ++i) {
            threads[i] = new PiCalculationThread(circleCount, simulations / threadsNumber);
            threads[i].start();
        }
        for (var thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return 2 * 2 * ((double) circleCount.get() / simulations);
    }
}
