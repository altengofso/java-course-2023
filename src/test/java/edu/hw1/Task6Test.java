package edu.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task6Test {
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
}
