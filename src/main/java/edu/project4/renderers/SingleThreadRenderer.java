package edu.project4.renderers;

import edu.project4.records.AffineCoefficients;
import edu.project4.records.FractalImage;
import edu.project4.records.Pixel;
import edu.project4.records.Point;
import edu.project4.records.Rect;
import edu.project4.transformations.Transformation;
import java.util.List;

public class SingleThreadRenderer extends AbstractRenderer {
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
        return canvas;
    }
}
