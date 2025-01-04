package backend.academy.transformations;

import backend.academy.primitives.Point;
import backend.academy.primitives.Rect;

public class DiscTransformation implements Transformation {
    private final Rect rect;

    public DiscTransformation(Rect rect) {
        this.rect = rect;
    }

    @Override
    public Point apply(Point point) {
        double scaledX = (point.x() - (rect.x() + rect.width() / 2)) / (rect.width() / 2);
        double scaledY = (point.y() - (rect.y() + rect.height() / 2)) / (rect.height() / 2);

        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan2(scaledX, scaledY);

        double x = theta * Math.sin(Math.PI * r) / Math.PI;
        double y = theta * Math.cos(Math.PI * r) / Math.PI;

        x = x * (rect.width() / 2) + rect.x() + rect.width() / 2;
        y = y * (rect.height() / 2) + rect.y() + rect.height() / 2;
        return new Point(x, y);
    }
}
