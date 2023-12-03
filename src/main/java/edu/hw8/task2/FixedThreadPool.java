package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.SneakyThrows;

public class FixedThreadPool implements ThreadPool {
    private final int threadCount;
    private final BlockingQueue<Runnable> taskQueue;
    private final Thread[] threads;
    private AtomicBoolean isAlive;

    public FixedThreadPool(int threadCount) {
        if (threadCount < 0) {
            throw new IllegalArgumentException("Number of threads should be positive");
        }
        this.threadCount = threadCount;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.threads = new Worker[threadCount];
        start();
    }

    @Override
    public void start() {
        isAlive = new AtomicBoolean(true);
        for (int i = 0; i < threadCount; ++i) {
            threads[i] = new Worker();
            threads[i].start();
        }
    }

    @Override
    @SneakyThrows
    public void execute(Runnable runnable) {
        taskQueue.put(runnable);
    }

    @Override
    public void close() throws Exception {
        isAlive.set(false);
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private class Worker extends Thread {
        @Override
        @SneakyThrows
        public void run() {
            while (isAlive.get()) {
                Runnable runnable;
                while ((runnable = taskQueue.poll()) != null) {
                    runnable.run();
                }
            }
        }
    }
}
