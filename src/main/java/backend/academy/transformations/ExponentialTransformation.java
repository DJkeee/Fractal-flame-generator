package backend.academy.transformations;

import backend.academy.primitives.Point;

public class ExponentialTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        return new Point(Math.exp(x - 1) * Math.cos(Math.PI * y), Math.exp(x - 1) * Math.sin(Math.PI * y));
    }

}
