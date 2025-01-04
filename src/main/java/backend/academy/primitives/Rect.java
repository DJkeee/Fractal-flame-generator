package backend.academy.primitives;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс Rect представляет прямоугольник, заданный координатами его нижнего левого угла
 * и размерами (шириной и высотой). Этот класс предоставляет методы для проверки,
 * содержится ли точка внутри прямоугольника, а также для генерации случайной точки внутри него.
 *
 * @param x      координата x нижнего левого угла прямоугольника
 * @param y      координата y нижнего левого угла прямоугольника
 * @param width  ширина прямоугольника
 * @param height высота прямоугольника
 */
public record Rect(double x, double y, double width, double height) {

    /**
     * Проверяет, содержится ли заданная точка внутри данного прямоугольника.
     *
     * @param point точка, которую необходимо проверить
     * @return true, если точка находится внутри прямоугольника; false в противном случае
     */
    public boolean contains(Point point) {
        return point.x() < getSideBorder() && point.y() < getTopBorder() && point.x() >= x && point.y() >= y;
    }

    /**
     * Возвращает координату правой границы прямоугольника.
     *
     * @return координата правой границы (x + width)
     */
    public double getSideBorder() {
        return x + width;
    }

    /**
     * Возвращает координату верхней границы прямоугольника.
     *
     * @return координата верхней границы (y + height)
     */
    public double getTopBorder() {
        return y + height;
    }

    /**
     * Генерирует случайную точку внутри данного прямоугольника.
     *
     * @param rect прямоугольник, внутри которого будет сгенерирована точка
     * @return случайная точка, находящаяся внутри прямоугольника
     */
    @SuppressFBWarnings(value = "PREDICTABLE_RANDOM", justification = "multithread random")
    public static Point generateRandomPoint(Rect rect) {
        double x = ThreadLocalRandom.current().nextDouble(rect.x, rect.getSideBorder());
        double y = ThreadLocalRandom.current().nextDouble(rect.y, rect.getTopBorder());
        return new Point(x, y);
    }
}

