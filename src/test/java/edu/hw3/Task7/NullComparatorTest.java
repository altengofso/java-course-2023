package edu.hw3.Task7;

import java.util.TreeMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class NullComparatorTest {
    @Test
    @DisplayName("Test NullComparator with TreeMap")
    void testNullComparatorWithTreeMap() {
        TreeMap<String, String> treeMap = new TreeMap<>(new NullComparator());
        treeMap.put(null, "test");
        assertThat(treeMap.containsKey(null)).isTrue();
    }
}
