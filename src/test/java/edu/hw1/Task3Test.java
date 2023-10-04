package edu.hw1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    @DisplayName("Вложение одного массива в другой")
    void testIsNestable() {
        assertTrue(Task3.isNestable(new int[] { 1, 2, 3, 4 }, new int[] { 0, 6 }));
        assertTrue(Task3.isNestable(new int[] { 3, 1 }, new int[] { 4, 0 }));
        assertTrue(Task3.isNestable(new int[] {}, new int[] {}));
        assertTrue(Task3.isNestable(new int[] {}, new int[] { 1 }));
        assertTrue(Task3.isNestable(new int[] {}, new int[] { 1, 2 }));
        assertTrue(Task3.isNestable(new int[] { 2 }, new int[] { 1, 3 }));

        assertFalse(Task3.isNestable(new int[] { 9, 9, 8 }, new int[] { 8, 9 }));
        assertFalse(Task3.isNestable(new int[] { 1, 2, 3, 4 }, new int[] { 2, 3 }));
        assertFalse(Task3.isNestable(new int[] { 1 }, new int[] { 2 }));
        assertFalse(Task3.isNestable(new int[] { 1 }, new int[] {}));
    }
}
