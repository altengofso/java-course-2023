package edu.hw7.Task1;

public final class AtomicIncrementor {
    private AtomicIncrementor() {
    }

    public static int run(int threadsNumber, int incrementCount) {
        Counter counter = new Counter();
        IncrementThread[] threads = new IncrementThread[threadsNumber];
        for (int i = 0; i < threadsNumber; ++i) {
            threads[i] = new IncrementThread(counter, incrementCount);
            threads[i].start();
        }
        for (int i = 0; i < threadsNumber; ++i) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return counter.get();
    }
}
