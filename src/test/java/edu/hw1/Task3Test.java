package edu.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    @DisplayName("Array is nestable in other array")
    void testIsNestable() {
        assertThat(Task3.isNestable(new int[] { 1, 2, 3, 4 }, new int[] { 0, 6 })).isTrue();
        assertThat(Task3.isNestable(new int[] { 3, 1 }, new int[] { 4, 0 })).isTrue();
        assertThat(Task3.isNestable(new int[] {}, new int[] {})).isTrue();
        assertThat(Task3.isNestable(new int[] {}, new int[] { 1 })).isTrue();
        assertThat(Task3.isNestable(new int[] {}, new int[] { 1, 2 })).isTrue();
        assertThat(Task3.isNestable(new int[] { 2 }, new int[] { 1, 3 })).isTrue();
    }

    @Test
    @DisplayName("Array is not nestable in other array")
    void testIsNotNestable() {
        assertThat(Task3.isNestable(new int[] { 9, 9, 8 }, new int[] { 8, 9 })).isFalse();
        assertThat(Task3.isNestable(new int[] { 1, 2, 3, 4 }, new int[] { 2, 3 })).isFalse();
        assertThat(Task3.isNestable(new int[] { 1 }, new int[] { 2 })).isFalse();
        assertThat(Task3.isNestable(new int[] { 1 }, new int[] {})).isFalse();
    }
}
