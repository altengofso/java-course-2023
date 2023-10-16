package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    static Arguments[] PopularCommandExecutor() {
        return new Arguments[] {
            Arguments.of(new Task3.DefaultConnectionManager(), 1),
            Arguments.of(new Task3.DefaultConnectionManager(), 10),
            Arguments.of(new Task3.FaultyConnectionManager(), 1),
            Arguments.of(new Task3.FaultyConnectionManager(), 10)
        };
    }

    @ParameterizedTest
    @MethodSource("PopularCommandExecutor")
    @DisplayName("Popular Command Executor")
    void testPopularCommandExecutor(Task3.ConnectionManager manager, int maxAttempts) {
        try {
            Task3.PopularCommandExecutor executor = new Task3.PopularCommandExecutor(manager, maxAttempts);
            executor.updatePackages();
        } catch (Throwable exception) {
            assertThat(exception.getMessage()).isEqualTo(
                "Failed to execute command after %d attempts.".formatted(maxAttempts));
            assertThat(exception.getCause().getMessage()).isEqualTo("Failed to execute command.");
        }
    }
}
