package edu.hw2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task3 {
    private static final String SAMPLE_COMMAND = "apt update && apt upgrade -y";
    private static final String LOG_ATTEMPT_NUMBER = "Attempt %d";
    private static final String LOG_COMMAND_SUCCESS = "Command executed successfully";
    private static final String LOG_COMMAND_FAIL = "Failed to execute command.";
    private static final String LOG_ATTEMPT_NUMBER_FAIL = "Attempt %d failed";
    private static final String LOG_COMMAND_FAILED_AFTER_NUMBER_ATTEMPTS =
        "Failed to execute command after %d attempts.";
    private static final String LOG_EXECUTING_COMMAND_WITH = "Executing command with %s: %s";
    private static final String LOG_CLOSING_CONNECTION = "Closing connection %s";
    private static final String LOG_REQUESTED_MANAGER = "Requested %s";
    private static final String LOG_CONNECTION_ESTABLISHED_WITH = "%s established with %s";
    private static final String LOG_FAILURE_PROBABILITY = "Failure Probability: %f";
    private static final Logger LOGGER = LogManager.getLogger();

    public interface Connection extends AutoCloseable {
        void execute(String command);
    }

    public interface ConnectionManager {
        Connection getConnection();
    }

    public static class ConnectionException extends RuntimeException {

        public ConnectionException(String message) {
            super(message);
        }

        public ConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static final class PopularCommandExecutor {
        private final ConnectionManager manager;
        private final int maxAttempts;

        public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
            this.manager = manager;
            this.maxAttempts = maxAttempts;
        }

        public void updatePackages() {
            tryExecute(SAMPLE_COMMAND);
        }

        public void tryExecute(String command) {
            Exception cause = null;
            for (int i = 1; i <= maxAttempts; ++i) {
                LOGGER.info(LOG_ATTEMPT_NUMBER.formatted(i));
                Connection connection = manager.getConnection();
                try (connection) {
                    connection.execute(command);
                    LOGGER.info(LOG_COMMAND_SUCCESS);
                    return;
                } catch (Exception exc) {
                    cause = exc;
                    LOGGER.info(LOG_ATTEMPT_NUMBER_FAIL.formatted(i));
                }
            }
            throw new ConnectionException(LOG_COMMAND_FAILED_AFTER_NUMBER_ATTEMPTS.formatted(maxAttempts), cause);
        }
    }

    private static class StableConnection implements Connection {

        @Override
        public void execute(String command) {
            LOGGER.info(LOG_EXECUTING_COMMAND_WITH.formatted(getClass().getSimpleName(), command));
        }

        @Override
        public void close() {
            LOGGER.info(LOG_CLOSING_CONNECTION.formatted(getClass().getSimpleName()));
        }
    }

    private static class FaultyConnection implements Connection {
        private final double failureProbability;

        private FaultyConnection() {
            this.failureProbability = Math.random();
            LOGGER.info(LOG_FAILURE_PROBABILITY.formatted(failureProbability));
        }

        @Override
        public void execute(String command) {
            LOGGER.info(LOG_EXECUTING_COMMAND_WITH.formatted(getClass().getSimpleName(), command));
            if (Math.random() <= failureProbability) {
                LOGGER.info(LOG_COMMAND_FAIL);
                throw new ConnectionException(LOG_COMMAND_FAIL);
            }
        }

        @Override
        public void close() {
            LOGGER.info(LOG_CLOSING_CONNECTION.formatted(getClass().getSimpleName()));
        }
    }

    public static class DefaultConnectionManager implements ConnectionManager {
        private final double faultyConnectionProbability;

        public DefaultConnectionManager() {
            this.faultyConnectionProbability = Math.random();
            LOGGER.info(LOG_REQUESTED_MANAGER.formatted(getClass().getSimpleName()));
            LOGGER.info(LOG_FAILURE_PROBABILITY.formatted(faultyConnectionProbability));
        }

        public Connection getConnection() {
            Connection connection;
            if (Math.random() <= faultyConnectionProbability) {
                connection = new FaultyConnection();
            } else {
                connection = new StableConnection();
            }
            LOGGER.info(LOG_CONNECTION_ESTABLISHED_WITH.formatted(
                connection.getClass().getSimpleName(),
                getClass().getSimpleName()
            ));
            return connection;
        }
    }

    public static class FaultyConnectionManager implements ConnectionManager {

        public FaultyConnectionManager() {
            LOGGER.info(LOG_REQUESTED_MANAGER.formatted(getClass().getSimpleName()));
        }

        @Override
        public Connection getConnection() {
            FaultyConnection faultyConnection = new FaultyConnection();
            LOGGER.info(LOG_CONNECTION_ESTABLISHED_WITH.formatted(
                faultyConnection.getClass().getSimpleName(),
                getClass().getSimpleName()
            ));
            return faultyConnection;
        }
    }
}

