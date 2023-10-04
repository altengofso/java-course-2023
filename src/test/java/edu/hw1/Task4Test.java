package edu.hw1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task4Test {
    @ParameterizedTest
    @CsvSource(textBlock = """
            '123456', '214365'
            'hTsii  s aimex dpus rtni.g', 'This is a mixed up string.'
            'badce', 'abcde'
            """)
    @DisplayName("Fix broken string")
    void testFixString(String broken, String normal) {
        assertEquals(Task4.fixString(broken), normal);
    }
}
