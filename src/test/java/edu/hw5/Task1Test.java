package edu.hw5;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw5.Task1.calculateAverageSessionTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    static Stream<Arguments> AverageSessionTime() {
        return Stream.of(
            Arguments.of(
                List.of("2022-03-12, 20:20 - 2022-03-12, 23:50", "2022-04-01, 21:30 - 2022-04-02, 01:20"),
                "3ч 40м"
            ),
            Arguments.of(
                List.of("2022-03-12, 20:20 - 2022-03-13, 01:50", "2022-04-01, 21:30 - 2022-04-02, 01:20"),
                "4ч 40м"
            )
        );
    }

    @ParameterizedTest
    @MethodSource("AverageSessionTime")
    void testAverageSessionTime(List<String> sessions, String averageDuration) {
        assertThat(calculateAverageSessionTime(sessions)).isEqualTo(averageDuration);
    }

    @Test
    void testAverageSessionTimeException() {
        assertThrows(IllegalArgumentException.class, () -> calculateAverageSessionTime(List.of("123 - 123")));
    }
}
