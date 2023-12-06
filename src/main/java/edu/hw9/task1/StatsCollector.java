package edu.hw9.task1;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

public class StatsCollector {
    private final ExecutorService executorService;
    private final BlockingQueue<MetricUpdate> queue;
    private final Map<String, MetricStats> metrics;

    public StatsCollector(int threadsCount) {
        queue = new LinkedBlockingQueue<>();
        metrics = new ConcurrentHashMap<>();
        executorService = Executors.newFixedThreadPool(threadsCount);
        for (int i = 0; i < threadsCount; ++i) {
            executorService.submit(this::processMetricUpdate);
        }
    }

    @SneakyThrows
    public void push(String metricName, double[] values) {
        if (executorService.isShutdown()) {
            throw new IllegalStateException("Service is shutting down, can't push now");
        }
        if (values.length == 0) {
            throw new IllegalArgumentException("Metric values can't be empty");
        }
        queue.put(new MetricUpdate(metricName, values));
    }

    public List<MetricStats> stats() {
        executorGracefulShutdown();
        return metrics.values().stream().toList();
    }

    @SneakyThrows
    private synchronized void processMetricUpdate() {
        while (!executorService.isShutdown()) {
            MetricUpdate metricUpdate = queue.take();
            MetricStats metricStatsUpdate = new MetricStats(metricUpdate.name(), metricUpdate.values());
            metrics.computeIfPresent(metricUpdate.name(), ((key, metricStats) -> {
                metricStats.updateStats(metricStatsUpdate);
                return metricStats;
            }));
            metrics.putIfAbsent(metricUpdate.name(), metricStatsUpdate);
        }
    }

    @SneakyThrows
    private void executorGracefulShutdown() {
        while (!queue.isEmpty()) {
        }
        executorService.shutdown();
        if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
            executorService.shutdownNow();
        }
    }
}
