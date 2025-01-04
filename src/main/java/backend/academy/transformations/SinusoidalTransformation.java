package backend.academy.transformations;

import backend.academy.primitives.Point;
import backend.academy.primitives.Rect;

public class SinusoidalTransformation implements Transformation {
    private final Rect rect;

    public SinusoidalTransformation(final Rect rect) {
        this.rect = rect;
    }

    @Override
    public Point apply(Point point) {
        double scaledX = (point.x() - (rect.x() + rect.width() / 2)) / (rect.width() / 2);
        double scaledY = (point.y() - (rect.y() + rect.height() / 2)) / (rect.height() / 2);

        double x = Math.sin(scaledX * Math.PI);
        double y = Math.sin(scaledY * Math.PI);

        x = x * (rect.width() / 2) + (rect.x() + rect.width() / 2);
        y = y * (rect.height() / 2) + (rect.y() + rect.height() / 2);

        return new Point(x, y);
    }
}
