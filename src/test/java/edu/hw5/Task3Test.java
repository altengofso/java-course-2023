package edu.hw5;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static edu.hw5.Task3.Task3.parseDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {
    static Stream<Arguments> ParseDate() {
        return Stream.of(
            Arguments.of("2020-10-10", Optional.of(LocalDate.of(2020, 10, 10))),
            Arguments.of("2020-12-2", Optional.of(LocalDate.of(2020, 12, 2))),
            Arguments.of("1/3/1976", Optional.of(LocalDate.of(1976, 3, 1))),
            Arguments.of("1/3/20", Optional.of(LocalDate.of(20, 3, 1))),
            Arguments.of("tomorrow", Optional.of(LocalDate.now().plusDays(1))),
            Arguments.of("today", Optional.of(LocalDate.now())),
            Arguments.of("yesterday", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("1 day ago", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("2234 days ago", Optional.of(LocalDate.now().minusDays(2234))),
            Arguments.of("1 day before", Optional.empty())
        );
    }

    @ParameterizedTest
    @MethodSource("ParseDate")
    void testParseDate(String date, Optional<LocalDate> localDate) {
        assertThat(parseDate(date)).isEqualTo(localDate);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2020-13-10", "2020-10-32", "32/3/1976", "1/13/1976"})
    void testParseDateException(String date) {
        assertThrows(DateTimeException.class, () -> parseDate(date));
    }
}
