package edu.hw8.task3;

import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordCrackerTest {
    static Stream<Arguments> PasswordCrackerArguments() {
        return Stream.of(
            Arguments.of(
                Map.of(
                    "a7b915cdc6697b0efa48641034eaa308", "user1",
                    "d61f1652fd3fdf3b798c2da9992aad4b", "user2"
                ),
                Map.of(
                    "user1", "pas1",
                    "user2", "pas2"
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("PasswordCrackerArguments")
    void testPasswordCracker(Map<String, String> crackingDatabase, Map<String, String> crackedDatabase) {
        PasswordCracker passwordCracker = new PasswordCracker();
        assertThat(passwordCracker.crackDatabase(crackingDatabase, 6, 4)).isEqualTo(crackedDatabase);
    }
}
