package edu.project4.transformations;

import edu.project4.records.Point;

public class HeartTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double sqrt = Math.sqrt(x * x + y * y);
        double newX = sqrt * Math.sin(sqrt * Math.atan(y / x));
        double newY = -sqrt * Math.cos(sqrt * Math.atan(y / x));
        return new Point(newX, newY);
    }
}
