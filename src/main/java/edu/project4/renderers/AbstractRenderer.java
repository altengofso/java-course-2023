package edu.project4.renderers;

import edu.project4.records.AffineCoefficients;
import edu.project4.records.Pixel;
import edu.project4.records.Point;
import edu.project4.transformations.Transformation;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public abstract class AbstractRenderer implements Renderer {
    protected static final int START = -20;

    protected AffineCoefficients[] getAffineCoefficients(int samples) {
        return Stream.generate(AffineCoefficients::create)
            .limit(samples)
            .toArray(AffineCoefficients[]::new);
    }

    protected AffineCoefficients getRandomAffineCoefficients(AffineCoefficients[] array) {
        return array[ThreadLocalRandom.current().nextInt(0, array.length)];
    }

    protected Transformation getRandomTransformation(List<Transformation> transformations) {
        return transformations.get(ThreadLocalRandom.current().nextInt(0, transformations.size()));
    }

    protected Point applyAffineCoefficients(AffineCoefficients affineCoefficients, Point point) {
        double x = affineCoefficients.a() * point.x() + affineCoefficients.b() * point.y() + affineCoefficients.c();
        double y = affineCoefficients.d() * point.x() + affineCoefficients.e() * point.y() + affineCoefficients.f();
        return new Point(x, y);
    }

    protected Point getRotatedPoint(Point point, double angle) {
        double x = point.x() * Math.cos(angle) - point.y() * Math.sin(angle);
        double y = point.x() * Math.sin(angle) + point.y() * Math.cos(angle);
        return new Point(x, y);
    }

    protected void setPixelColor(Pixel pixel, AffineCoefficients affineCoefficients) {
        Color color = affineCoefficients.color();
        if (pixel.getHitCount() == 0) {
            pixel.setR(color.getRed());
            pixel.setG(color.getGreen());
            pixel.setB(color.getBlue());
        } else {
            pixel.setR((pixel.getR() + color.getRed()) / 2);
            pixel.setG((pixel.getG() + color.getGreen()) / 2);
            pixel.setB((pixel.getB() + color.getBlue()) / 2);
        }
        pixel.setHitCount(pixel.getHitCount() + 1);
    }
}
