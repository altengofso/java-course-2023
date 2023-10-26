package edu.hw3;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task4Test {
    static Stream<Arguments> ConvertToRomanArguments() {
        return Stream.of(
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(3999, "MMMCMXCIX")
        );
    }

    @ParameterizedTest
    @MethodSource("ConvertToRomanArguments")
    @DisplayName("Test Convert To Roman")
    void testConvertToRoman(int arabic, String roman) {
        assertThat(Task4.convertToRoman(arabic)).isEqualTo(roman);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 4000})
    @DisplayName("Test Convert To Roman IllegalArgumentException")
    void testConvertToRomanIllegalArgumentException(int arabic) {
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(arabic));
    }
}
