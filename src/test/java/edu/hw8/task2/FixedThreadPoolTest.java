package edu.hw8.task2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FixedThreadPoolTest {
    private int fibonacci(int number) {
        if (number == 0 || number == 1) {
            return number;
        }
        return fibonacci(number - 1) + fibonacci(number - 2);
    }

    @Test
    @SneakyThrows
    void testFibonacciWithCustomFixedThreadPool() {
        ThreadPool threadPool = new FixedThreadPool(5);
        List<Integer> expected = List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55);
        List<Integer> actual = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 11; ++i) {
            final int number = i;
            threadPool.execute(() -> actual.add(fibonacci(number)));
        }
        threadPool.close();
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}
