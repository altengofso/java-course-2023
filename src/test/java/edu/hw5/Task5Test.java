package edu.hw5;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw5.Task5.isCarNumberValid;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    static Stream<Arguments> IsCarNumberValid() {
        return Stream.of(
            Arguments.of("А123ВЕ777", true),
            Arguments.of("О777ОО177", true),
            Arguments.of("123АВЕ777", false),
            Arguments.of("А123ВГ77", false),
            Arguments.of("А123ВЕ7777", false)
        );
    }

    @ParameterizedTest
    @MethodSource("IsCarNumberValid")
    void testIsCarNumberValid(String carNumber, boolean result) {
        assertThat(isCarNumberValid(carNumber)).isEqualTo(result);
    }
}
