package edu.hw5;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw5.Task4.isPasswordSecure;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    static Stream<Arguments> IsPasswordSecure() {
        return Stream.of(
            Arguments.of("1~", true),
            Arguments.of("2!", true),
            Arguments.of("3@", true),
            Arguments.of("4#", true),
            Arguments.of("5$", true),
            Arguments.of("6%", true),
            Arguments.of("7^", true),
            Arguments.of("8&", true),
            Arguments.of("9*", true),
            Arguments.of("10|", true),
            Arguments.of("11_", false)
        );
    }

    @ParameterizedTest
    @MethodSource("IsPasswordSecure")
    void testIsPasswordSecure(String password, boolean result) {
        assertThat(isPasswordSecure(password)).isEqualTo(result);
    }
}
