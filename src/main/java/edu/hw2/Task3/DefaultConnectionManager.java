package edu.hw2.Task3;

import static edu.hw2.Task3.LogStrings.LOG_CONNECTION_ESTABLISHED_WITH;
import static edu.hw2.Task3.LogStrings.LOG_FAILURE_PROBABILITY;
import static edu.hw2.Task3.LogStrings.LOG_REQUESTED_MANAGER;
import static edu.hw2.Task3.PopularCommandExecutor.LOGGER;

public class DefaultConnectionManager implements ConnectionManager {
    private final double faultyConnectionProbability;

    public DefaultConnectionManager() {
        this.faultyConnectionProbability = Math.random();
        LOGGER.info(LOG_REQUESTED_MANAGER, getClass().getSimpleName());
        LOGGER.info(LOG_FAILURE_PROBABILITY, faultyConnectionProbability);
    }

    public DefaultConnectionManager(double faultyConnectionProbability) {
        this.faultyConnectionProbability = faultyConnectionProbability;
        LOGGER.info(LOG_REQUESTED_MANAGER, getClass().getSimpleName());
        LOGGER.info(LOG_FAILURE_PROBABILITY, faultyConnectionProbability);
    }

    public Connection getConnection() {
        Connection connection;
        if (Math.random() <= faultyConnectionProbability) {
            connection = new FaultyConnection(faultyConnectionProbability);
        } else {
            connection = new StableConnection();
        }
        LOGGER.info(
            LOG_CONNECTION_ESTABLISHED_WITH,
            connection.getClass().getSimpleName(),
            getClass().getSimpleName()
        );
        return connection;
    }
}
