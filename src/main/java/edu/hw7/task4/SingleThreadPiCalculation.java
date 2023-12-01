package edu.hw7.task4;

import java.util.Random;

public final class SingleThreadPiCalculation {
    private SingleThreadPiCalculation() {
    }

    public static double calculatePI(int simulations) {
        Random random = new Random();
        int circleCount = 0;
        for (int i = 0; i < simulations; ++i) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            if (x * x + y * x <= 1) {
                circleCount++;
            }
        }
        return 2 * 2 * ((double) circleCount / simulations);
    }
}
