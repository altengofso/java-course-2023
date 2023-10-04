package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @ParameterizedTest
    @CsvSource(value = {
            "01:00, 60",
            "13:56, 836",
            "10:60, -1",
            "-1:30, -1",
            "2:-1, -1",
            "2:abc, -1"
    })
    @DisplayName("Перевод строки формата mm:ss в общее количество секунд")
    void testMinutesToSeconds(String inputString, int totalSeconds) {
        assertThat(Task1.minutesToSeconds(inputString)).isEqualTo(totalSeconds);
    }
}
