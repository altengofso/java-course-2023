package edu.hw2.Task3;

import static edu.hw2.Task3.LogStrings.LOG_CLOSING_CONNECTION;
import static edu.hw2.Task3.LogStrings.LOG_EXECUTING_COMMAND_WITH;
import static edu.hw2.Task3.PopularCommandExecutor.LOGGER;

public class StableConnection implements Connection {
    @Override
    public void execute(String command) {
        LOGGER.info(LOG_EXECUTING_COMMAND_WITH, getClass().getSimpleName(), command);
    }

    @Override
    public void close() {
        LOGGER.info(LOG_CLOSING_CONNECTION, getClass().getSimpleName());
    }
}
