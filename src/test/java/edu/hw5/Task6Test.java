package edu.hw5;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw5.Task6.isSubstring;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {
    static Stream<Arguments> IsSubstring() {
        return Stream.of(
            Arguments.of("abc", "achfdbaabgabcaabg", true),
            Arguments.of("abc", "achfdbaabgabdaabg", false)
        );
    }

    @ParameterizedTest
    @MethodSource("IsSubstring")
    void testIsSubstring(String subString, String string, boolean result) {
        assertThat(isSubstring(subString, string)).isEqualTo(result);
    }
}
