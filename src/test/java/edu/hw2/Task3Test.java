package edu.hw2;

import edu.hw2.Task3.ConnectionException;
import edu.hw2.Task3.ConnectionManager;
import edu.hw2.Task3.DefaultConnectionManager;
import edu.hw2.Task3.FaultyConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {
    static Arguments[] AlwaysSuccessfulPopularCommandExecutor() {
        return new Arguments[] {
            Arguments.of(new DefaultConnectionManager(0), 10),
            Arguments.of(new FaultyConnectionManager(0), 10)
        };
    }

    @ParameterizedTest
    @MethodSource("AlwaysSuccessfulPopularCommandExecutor")
    @DisplayName("Always Successful Popular Command Executor")
    void testSuccessfulPopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);
        assertDoesNotThrow(executor::updatePackages);
    }

    static Arguments[] AlwaysFailingPopularCommandExecutor() {
        return new Arguments[] {
            Arguments.of(new DefaultConnectionManager(1), 10),
            Arguments.of(new FaultyConnectionManager(1), 10)
        };
    }

    @ParameterizedTest
    @MethodSource("AlwaysFailingPopularCommandExecutor")
    @DisplayName("Always Failing Popular Command Executor")
    void testFailingPopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);
        Throwable exception = assertThrows(ConnectionException.class, executor::updatePackages);
        assertThat(exception.getMessage()).isEqualTo(
            "Failed to execute command after %d attempts.".formatted(maxAttempts));
        assertThat(exception.getCause().getMessage()).isEqualTo("Failed to execute command.");
    }

    static Arguments[] NormalPopularCommandExecutor() {
        return new Arguments[] {
            Arguments.of(new DefaultConnectionManager(), 10),
            Arguments.of(new FaultyConnectionManager(), 10)
        };
    }

    @ParameterizedTest
    @MethodSource("NormalPopularCommandExecutor")
    @DisplayName("Normal Popular Command Executor")
    void testNormalPopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        try {
            PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);
            executor.updatePackages();
        } catch (ConnectionException exception) {
            assertThat(exception.getMessage()).isEqualTo(
                "Failed to execute command after %d attempts.".formatted(maxAttempts));
            assertThat(exception.getCause().getMessage()).isEqualTo("Failed to execute command.");
        }
    }
}
