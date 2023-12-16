package edu.hw10.task2;

import edu.hw10.task2.test_classes.FibonacciCache;
import edu.hw10.task2.test_classes.FibonacciCacheImpl;
import edu.hw10.task2.test_classes.FibonacciPersistentCache;
import edu.hw10.task2.test_classes.FibonacciPersistentCacheImpl;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.util.HashMap;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;

public class CacheProxyTest {
    @TempDir
    private static Path tempPath;

    @Test
    @SneakyThrows
    void testPersisntentCacheProxy() {
        String file = tempPath.resolve("cache").toString();
        FibonacciPersistentCache fibonacciPersistentCache = CacheProxy.create(
            new FibonacciPersistentCacheImpl(),
            FibonacciPersistentCacheImpl.class,
            file
        );

        long calculated = fibonacciPersistentCache.getFibonacci(5);
        long cached = fibonacciPersistentCache.getFibonacci(5);

        assertThat(calculated).isEqualTo(cached);

        HashMap<String, Object> actual;
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            //noinspection unchecked
            actual = (HashMap<String, Object>) objectInputStream.readObject();
        }
        HashMap<String, Object> expected = new HashMap<>();
        expected.put("getFibonacci[5]", 5L);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testInMemoryCacheProxy() {
        String file = tempPath.resolve("cache").toString();
        FibonacciCache fibonacciCache = CacheProxy.create(
            new FibonacciCacheImpl(),
            FibonacciCacheImpl.class,
            file
        );

        long calculated = fibonacciCache.getFibonacci(5);
        long cached = fibonacciCache.getFibonacci(5);

        assertThat(calculated).isEqualTo(cached);
    }
}
