package edu.project4;

import edu.project4.image.ImageFormat;
import edu.project4.image.ImageGammaCorrection;
import edu.project4.records.Rect;
import edu.project4.renderers.MultiThreadRenderer;
import edu.project4.renderers.SingleThreadRenderer;
import edu.project4.transformations.HeartTransformation;
import java.nio.file.Path;
import java.util.List;
import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@UtilityClass
public final class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String FILENAME = "image.png";

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        long startTimeSingle = System.nanoTime();
        FractalGenerator singleThreadFractalGenerator = new FractalGenerator(
            3000, 3000,
            new SingleThreadRenderer(),
            new Rect(-2.5, -3.5, 5, 7.0),
            List.of(
                new HeartTransformation()
            ),
            new ImageGammaCorrection()
        );
        singleThreadFractalGenerator.generate(Path.of(FILENAME), ImageFormat.PNG, 20, 10000000, 2);
        long finishTimeSingle = System.nanoTime();
        long spentTimeSingle = finishTimeSingle - startTimeSingle;

        long startTimeMulti = System.nanoTime();
        FractalGenerator multiThreadFractalGenerator = new FractalGenerator(
            3000, 3000,
            new MultiThreadRenderer(),
            new Rect(-2.5, -3.5, 5, 7.0),
            List.of(
                new HeartTransformation()
            ),
            new ImageGammaCorrection()
        );
        multiThreadFractalGenerator.generate(Path.of(FILENAME), ImageFormat.PNG, 20, 10000000, 2);
        long finishTimeMulti = System.nanoTime();
        long spentTimeMulti = finishTimeMulti - startTimeMulti;
        LOGGER.info("Acceleration of fractal flame generation: %f times".formatted(
            (double) spentTimeSingle / spentTimeMulti));
    }
}
