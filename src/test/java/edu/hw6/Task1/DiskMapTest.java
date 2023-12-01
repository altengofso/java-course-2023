package edu.hw6.Task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DiskMapTest {

    @Test
    void testDiskMap() throws IOException {
        DiskMap diskMap = new DiskMap("DiskMapTest.txt");
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        diskMap.put("key3", "value3");
        assertThat(diskMap.get("key1")).isEqualTo("value1");
        assertThat(diskMap.remove("key1")).isEqualTo("value1");
        assertThat(diskMap.containsKey("key1")).isFalse();
        assertThat(diskMap.size()).isEqualTo(2);
        DiskMap anotherDiskMap = new DiskMap("DiskMapTest.txt");
        assertThat(anotherDiskMap.size()).isEqualTo(2);
        assertThat(anotherDiskMap.containsValue("value2")).isTrue();
        diskMap.clear();
        assertThat(diskMap.isEmpty()).isTrue();
    }

    @AfterAll
    static void deleteTempFile() throws IOException {
        Files.delete(Path.of("DiskMapTest.txt"));
    }
}
