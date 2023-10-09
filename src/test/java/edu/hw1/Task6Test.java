package edu.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class Task6Test {
    @ParameterizedTest
    @MethodSource("KaprekarProvider")
    @DisplayName("Count steps to Kaprekar Const")
    void testCountK(int number, int steps) {
        assertThat(Task6.countK(number)).isEqualTo(steps);
    }

    static Stream<Arguments> KaprekarProvider() {
        return Stream.of(
                Arguments.of(6621, 5),
                Arguments.of(6554, 4),
                Arguments.of(1234, 3));
    }

    @ParameterizedTest
    @ValueSource(ints = { 999, 1000, 10000 })
    @DisplayName("Count steps to Kaprekar Const: IllegalArgumentException")
    void testCountKThrowsIllegalArgumentException(int number) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Task6.countK(number));
        assertThat(exception.getMessage()).isEqualTo("Number should be in range from 1001 to 9999");
    }
}
