package backend.academy.transformations;

import backend.academy.primitives.Point;

public class ExTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan2(point.x(), point.y());
        double p0 = Math.sin(theta + r);
        double p1 = Math.cos(theta - r);
        double p03 = p0 * p0 * p0;
        double p13 = p1 * p1 * p1;
        return new Point(r * (p03 + p13) * 2, r * (p03 - p13) * 2);
    }
}
