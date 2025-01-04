package backend.academy.transformations;

import backend.academy.primitives.Point;

public class LinearTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return point;
    }
}
