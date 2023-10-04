package edu.hw1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    @DisplayName("Исправление сломанной строки")
    void testFixString() {
        assertEquals(Task4.fixString("123456"), "214365");
        assertEquals(Task4.fixString("hTsii  s aimex dpus rtni.g"), "This is a mixed up string.");
        assertEquals(Task4.fixString("badce"), "abcde");
    }
}
