package backend.academy.transformations;

import backend.academy.primitives.Point;
import backend.academy.primitives.Rect;

public class HeartTransformation implements Transformation {
    private final Rect rect;

    public HeartTransformation(Rect rect) {
        this.rect = rect;
    }

    public Point apply(Point point) {
        double scaledX = (point.x() - (rect.x() + rect.width() / 2)) / (rect.width() / 2);
        double scaledY = (point.y() - (rect.y() + rect.height() / 2)) / (rect.height() / 2);

        double r = Math.sqrt(scaledX * scaledX + scaledY * scaledY);
        double theta = Math.atan2(scaledY, scaledX);

        double x = r * Math.sin(theta * r);
        double y = -r * Math.cos(theta * r);

        x = x * (rect.width() / 2) + (rect.x() + rect.width() / 2);
        y = y * (rect.height() / 2) + (rect.y() + rect.height() / 2);

        return new Point(x, y);

    }
}
