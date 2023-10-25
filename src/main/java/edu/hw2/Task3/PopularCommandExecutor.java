package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw2.Task3.LogStrings.LOG_ATTEMPT_NUMBER;
import static edu.hw2.Task3.LogStrings.LOG_ATTEMPT_NUMBER_FAIL;
import static edu.hw2.Task3.LogStrings.LOG_COMMAND_SUCCESS;
import static edu.hw2.Task3.LogStrings.SAMPLE_COMMAND;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;
    static final Logger LOGGER = LogManager.getLogger();

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
            LOGGER.info(LOG_ATTEMPT_NUMBER, i);
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                LOGGER.info(LOG_COMMAND_SUCCESS);
                return;
            } catch (Exception exc) {
                LOGGER.info(LOG_ATTEMPT_NUMBER_FAIL, i);
                if (i == maxAttempts) {
                    cause = exc;
                }
            }
        }
        throw new ConnectionException("Failed to execute command after %d attempts.".formatted(maxAttempts), cause);
    }
}
