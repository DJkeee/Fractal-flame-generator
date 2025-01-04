package backend.academy.transformations;

import backend.academy.primitives.Point;
import backend.academy.primitives.Rect;

public class DiamondTransformation implements Transformation {
    private final Rect rect;

    public DiamondTransformation(Rect rect) {
        this.rect = rect;
    }

    @Override
    public Point apply(Point point) {
        double scaleX = (point.x() - (rect.x() + rect.width() / 2)) / (rect.width() / 2);
        double scaleY = (point.y() - (rect.y() + rect.height() / 2)) / (rect.height() / 2);

        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan2(scaleX, scaleY);

        double x = Math.sin(theta) * Math.cos(r);
        double y = Math.cos(theta) * Math.sin(r);
        x = x * (rect.width() / 2) + rect.x() + rect.width() / 2;
        y = y * (rect.height() / 2) + rect.y() + rect.height() / 2;
        return new Point(x, y);
    }
}
