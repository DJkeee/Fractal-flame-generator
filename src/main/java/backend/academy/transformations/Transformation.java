package backend.academy.transformations;

import backend.academy.primitives.Point;
import java.util.function.Function;

@FunctionalInterface
public interface Transformation extends Function<Point, Point> {
}
