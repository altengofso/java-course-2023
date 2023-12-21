package edu.hw9.task1;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StatsCollectorTest {
    @SneakyThrows @Test
    void testStatsCollector() {
        StatsCollector collector = new StatsCollector(4);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        var futures = Stream.generate(() -> CompletableFuture.runAsync(() -> {
                collector.push("temperature", new double[] {20, 16, 18});
                collector.push("windSpeed", new double[] {6, 10, 8});
            }, executorService))
            .limit(10)
            .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        executorService.close();
        Thread.sleep(500);
        var stats = collector.stats();
        assertThat(stats.get()).isEqualTo(List.of(
            new MetricStats("temperature", 540, 16, 20, 18),
            new MetricStats("windSpeed", 240, 6, 10, 8)
        ));
        collector.stop();
    }
}
