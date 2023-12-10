package edu.project4.renderers;

import edu.project4.records.AffineCoefficients;
import edu.project4.records.FractalImage;
import edu.project4.records.Pixel;
import edu.project4.records.Point;
import edu.project4.records.Rect;
import edu.project4.transformations.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

public class MultiThreadRenderer extends AbstractRenderer {
    private final ExecutorService executorService =
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @SneakyThrows
    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> transformations,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        AffineCoefficients[] affineCoefficientsArray = getAffineCoefficients(samples);
        for (int i = 0; i < samples; ++i) {
            executorService.execute(() -> executorTask(
                canvas,
                world,
                transformations,
                iterPerSample,
                symmetry,
                affineCoefficientsArray
            ));
        }
        executorService.shutdown();
        if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
            executorService.shutdownNow();
        }
        return canvas;
    }

    private void executorTask(
        FractalImage canvas,
        Rect world,
        List<Transformation> transformations,
        int iterPerSample,
        int symmetry,
        AffineCoefficients[] affineCoefficientsArray
    ) {
        Point point = world.getRandomPoint();
        for (int iter = START; iter < iterPerSample; ++iter) {
            AffineCoefficients affineCoefficients = getRandomAffineCoefficients(affineCoefficientsArray);
            point = applyAffineCoefficients(affineCoefficients, point);
            point = getRandomTransformation(transformations).apply(point);
            double angle = 0;
            for (int j = 0; j < symmetry; j++) {
                angle += 2 * Math.PI / symmetry;
                Point rotatedPoint = getRotatedPoint(point, angle);
                if (world.contains(rotatedPoint)) {
                    int x = (int) ((rotatedPoint.x() - world.x()) * canvas.width() / world.width());
                    int y = (int) ((rotatedPoint.y() - world.y()) * canvas.height() / world.height());
                    Pixel pixel = canvas.getPixel(x, y);
                    if (pixel != null) {
                        setPixelColor(pixel, affineCoefficients);
                    }
                }
            }
        }
    }
}
