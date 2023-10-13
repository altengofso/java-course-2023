package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task4Test {
    @ParameterizedTest
    @CsvSource(textBlock = """
            '123456', '214365'
            'hTsii  s aimex dpus rtni.g', 'This is a mixed up string.'
            'badce', 'abcde'
            """)
    @DisplayName("Fix broken string")
    void testFixString(String broken, String fixed) {
        assertThat(Task4.fixString(broken)).isEqualTo(fixed);
    }
}
