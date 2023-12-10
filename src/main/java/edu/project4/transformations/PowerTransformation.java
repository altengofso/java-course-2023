package edu.project4.transformations;

import edu.project4.records.Point;

public class PowerTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double theta = Math.atan(y / x);
        double sqrt = Math.sqrt(x * x + y * y);
        double newX = Math.pow(sqrt, Math.sin(theta)) * Math.cos(theta);
        double newY = Math.pow(sqrt, Math.sin(theta)) * Math.sin(theta);
        return new Point(newX, newY);
    }
}
