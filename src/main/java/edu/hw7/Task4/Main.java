package edu.hw7.Task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int[] SIMULATIONS = {10000000, 100000000, 1000000000};
    private final static int THREADS_NUMBER = 6;

    public static void main(String[] args) {
        for (int simulations : SIMULATIONS) {
            long startTimeSingle = System.nanoTime();
            SingleThreadPiCalculation.calculatePI(simulations);
            long finishTimeSingle = System.nanoTime();
            long spentTimeSingle = finishTimeSingle - startTimeSingle;

            long startTimeMulti = System.nanoTime();
            double pi = MultiThreadPiCalculation.calculatePI(simulations, THREADS_NUMBER);
            long finishTimeMulti = System.nanoTime();
            long spentTimeMulti = finishTimeMulti - startTimeMulti;

            LOGGER.info("Acceleration of calculation for %d simulations: %f times".formatted(
                simulations,
                (double) spentTimeSingle / spentTimeMulti
            ));
            LOGGER.info("Offset from PI: %f".formatted(Math.PI - pi));
        }
    }

    private Main() {
    }
}
