package edu.hw2.Task3;

import static edu.hw2.Task3.LogStrings.LOG_CLOSING_CONNECTION;
import static edu.hw2.Task3.LogStrings.LOG_COMMAND_FAIL;
import static edu.hw2.Task3.LogStrings.LOG_EXECUTING_COMMAND_WITH;
import static edu.hw2.Task3.LogStrings.LOG_FAILURE_PROBABILITY;
import static edu.hw2.Task3.PopularCommandExecutor.LOGGER;

public class FaultyConnection implements Connection {
    private final double failureProbability;

    public FaultyConnection(double failureProbability) {
        this.failureProbability = failureProbability;
        LOGGER.info(LOG_FAILURE_PROBABILITY, failureProbability);
    }

    @Override
    public void execute(String command) {
        LOGGER.info(LOG_EXECUTING_COMMAND_WITH, getClass().getSimpleName(), command);
        if (Math.random() <= failureProbability) {
            LOGGER.info(LOG_COMMAND_FAIL);
            throw new ConnectionException(LOG_COMMAND_FAIL);
        }
    }

    @Override
    public void close() {
        LOGGER.info(LOG_CLOSING_CONNECTION, getClass().getSimpleName());
    }
}
