package edu.hw7.task4;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.SneakyThrows;

public final class MultiThreadPiCalculation {
    private MultiThreadPiCalculation() {
    }

    @SneakyThrows
    public static double calculatePI(int simulations, int threadsNumber) {
        AtomicInteger circleCount = new AtomicInteger(0);
        PiCalculationThread[] threads = new PiCalculationThread[threadsNumber];
        for (int i = 0; i < threadsNumber; ++i) {
            threads[i] = new PiCalculationThread(circleCount, simulations / threadsNumber);
            threads[i].start();
        }
        for (var thread : threads) {
            thread.join();
        }
        return 2 * 2 * ((double) circleCount.get() / simulations);
    }
}
