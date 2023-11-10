package edu.hw5;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw5.Task2.findFridays13;
import static edu.hw5.Task2.findNextFriday13;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    static Stream<Arguments> FindFridays13() {
        return Stream.of(
            Arguments.of(
                1925,
                List.of(
                    LocalDate.of(1925, 2, 13),
                    LocalDate.of(1925, 3, 13),
                    LocalDate.of(1925, 11, 13)
                )
            ),
            Arguments.of(
                2024,
                List.of(
                    LocalDate.of(2024, 9, 13),
                    LocalDate.of(2024, 12, 13)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("FindFridays13")
    void testFindFridays13(int year, List<LocalDate> dates) {
        assertThat(findFridays13(year)).isEqualTo(dates);
    }

    static Stream<Arguments> FindNextFriday13() {
        return Stream.of(
            Arguments.of(
                LocalDate.of(1925, 1, 1),
                LocalDate.of(1925, 2, 13)
            ),
            Arguments.of(
                LocalDate.of(2023, 11, 10),
                LocalDate.of(2024, 9, 13)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("FindNextFriday13")
    void testFindNextFridays13(LocalDate date, LocalDate friday13) {
        assertThat(findNextFriday13(date)).isEqualTo(friday13);
    }
}
