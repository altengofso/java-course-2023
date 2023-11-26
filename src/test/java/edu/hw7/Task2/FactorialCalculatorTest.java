package edu.hw7.Task2;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorialCalculatorTest {
    static Stream<Arguments> FactorialCalculatorArguments() {
        return Stream.of(
            Arguments.of(0, 1),
            Arguments.of(1, 1),
            Arguments.of(5, 120)
        );
    }

    @ParameterizedTest
    @MethodSource("FactorialCalculatorArguments")
    void testFactorialCalculator(int number, long expected) {
        assertThat(FactorialCalculator.calculateFactorial(number)).isEqualTo(expected);
    }

    @Test
    void testFactorialCalculatorThrows() {
        assertThrows(IllegalArgumentException.class, () -> FactorialCalculator.calculateFactorial(-1));
    }
}
