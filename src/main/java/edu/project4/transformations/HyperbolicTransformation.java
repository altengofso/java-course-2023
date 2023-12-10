package edu.project4.transformations;

import edu.project4.records.Point;

public class HyperbolicTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double theta = Math.atan(y / x);
        double sqrt = Math.sqrt(x * x + y * y);
        double newX = Math.sin(theta) / sqrt;
        double newY = sqrt * Math.cos(theta);
        return new Point(newX, newY);
    }
}
