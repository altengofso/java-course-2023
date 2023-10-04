package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @ParameterizedTest
    @CsvSource(value = {
            "4666, 4",
            "544, 3",
            "1, 1",
            "-1, 1",
            "0, 1"
    })
    @DisplayName("Подсчет цифр в числе")
    void testCountDigits(int number, int digits) {
        assertThat(Task2.countDigits(number)).isEqualTo(digits);
    }
}
