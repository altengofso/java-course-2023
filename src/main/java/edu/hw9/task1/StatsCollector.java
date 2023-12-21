package edu.hw9.task1;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

public class StatsCollector {
    private final ExecutorService updateExecutorService;
    private final ExecutorService statsExecutorService;
    private final BlockingQueue<MetricUpdate> queue;
    private final Map<String, MetricStats> metrics;

    public StatsCollector(int threadsCount) {
        queue = new LinkedBlockingQueue<>();
        metrics = new ConcurrentHashMap<>();
        updateExecutorService = Executors.newFixedThreadPool(threadsCount);
        statsExecutorService = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < threadsCount; ++i) {
            updateExecutorService.submit(this::processMetricUpdate);
        }
    }

    @SneakyThrows
    public void push(String metricName, double[] values) {
        if (updateExecutorService.isShutdown()) {
            throw new IllegalStateException("Service is shutting down, can't push now");
        }
        if (values.length == 0) {
            throw new IllegalArgumentException("Metric values can't be empty");
        }
        queue.put(new MetricUpdate(metricName, values));
    }

    public Future<List<MetricStats>> stats() {
        return statsExecutorService.submit(this::getMetricStatsList);
    }

    private List<MetricStats> getMetricStatsList() {
        return metrics.values().stream().toList();
    }

    @SneakyThrows
    public void stop() {
        stopUpdateExecutorService();
        stopStatsExecutorService();
    }

    @SneakyThrows
    private void stopUpdateExecutorService() {
        while (!queue.isEmpty()) {
        }
        updateExecutorService.shutdown();
        if (!updateExecutorService.awaitTermination(1, TimeUnit.SECONDS)) {
            updateExecutorService.shutdownNow();
        }
    }

    @SneakyThrows
    private void stopStatsExecutorService() {
        statsExecutorService.shutdown();
        if (!statsExecutorService.awaitTermination(1, TimeUnit.SECONDS)) {
            statsExecutorService.shutdownNow();
        }
    }

    @SneakyThrows
    private void processMetricUpdate() {
        while (!updateExecutorService.isShutdown()) {
            MetricUpdate metricUpdate = queue.take();
            MetricStats metricStatsUpdate = new MetricStats(metricUpdate.name(), metricUpdate.values());
            metrics.computeIfPresent(metricUpdate.name(), ((key, metricStats) -> {
                metricStats.updateStats(metricStatsUpdate);
                return metricStats;
            }));
            metrics.putIfAbsent(metricUpdate.name(), metricStatsUpdate);
        }
    }
}
