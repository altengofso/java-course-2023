package edu.hw7.Task4;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PiCalculationTest {
    private final static int SIMULATIONS = 10000000;
    private final static int THREADS_NUMBER = 4;

    @Test
    void testMultiThreadPiCalculationFasterThanSingle() {
        long startTimeSingle = System.nanoTime();
        SingleThreadPiCalculation.calculatePI(SIMULATIONS);
        long finishTimeSingle = System.nanoTime();
        long spentTimeSingle = finishTimeSingle - startTimeSingle;

        long startTimeMulti = System.nanoTime();
        MultiThreadPiCalculation.calculatePI(SIMULATIONS, THREADS_NUMBER);
        long finishTimeMulti = System.nanoTime();
        long spentTimeMulti = finishTimeMulti - startTimeMulti;

        assertThat(spentTimeMulti).isLessThan(spentTimeSingle);
    }
}
