package edu.hw7.task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public final class AtomicIncrementor {
    private final AtomicInteger counter = new AtomicInteger(0);

    public int run(int threadsNumber, int incrementCount) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(threadsNumber)) {
            for (int i = 0; i < incrementCount; ++i) {
                executorService.submit(this::increment);
            }
        }
        return counter.get();
    }

    private void increment() {
        counter.incrementAndGet();
    }
}
