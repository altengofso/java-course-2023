package edu.hw2.Task3;

import static edu.hw2.Task3.LogStrings.LOG_CONNECTION_ESTABLISHED_WITH;
import static edu.hw2.Task3.LogStrings.LOG_REQUESTED_MANAGER;
import static edu.hw2.Task3.PopularCommandExecutor.LOGGER;

public class FaultyConnectionManager implements ConnectionManager {
    private final double failureProbability;

    public FaultyConnectionManager() {
        failureProbability = Math.random();
        LOGGER.info(LOG_REQUESTED_MANAGER, getClass().getSimpleName());
    }

    public FaultyConnectionManager(double failureProbability) {
        this.failureProbability = failureProbability;
        LOGGER.info(LOG_REQUESTED_MANAGER, getClass().getSimpleName());
    }

    @Override
    public Connection getConnection() {
        FaultyConnection faultyConnection = new FaultyConnection(failureProbability);
        LOGGER.info(
            LOG_CONNECTION_ESTABLISHED_WITH,
            faultyConnection.getClass().getSimpleName(),
            getClass().getSimpleName()
        );
        return faultyConnection;
    }
}
