package backend.academy.fractalimage;

import backend.academy.primitives.Pixel;
import backend.academy.primitives.Point;
import backend.academy.primitives.Rect;

/**
 * Класс FractalImageLogic предоставляет статические методы для работы с фрактальными изображениями.
 * Он содержит логику, связанную с преобразованием координат точек из прямоугольной области
 * в пиксели изображения.
 */
public class FractalImageLogic {

    // Приватный конструктор для предотвращения создания экземпляров этого класса
    private FractalImageLogic() {
    }

    /**
     * Преобразует координаты точки из заданного прямоугольника в пиксели изображения.
     *
     * @param image фрактальное изображение, в котором необходимо получить пиксель
     * @param rect  прямоугольная область, содержащая точку
     * @param point точка, координаты которой нужно преобразовать
     * @return пиксель изображения, соответствующий заданной точке, или null,
     *     если точка не содержится в прямоугольнике
     */
    public static Pixel pixelScaleFromRectToImage(FractalImage image, Rect rect, Point point) {
        if (!rect.contains(point)) {
            return null;
        }

        double xScale = (double) image.width() / rect.width();
        double yScale = (double) image.height() / rect.height();
        int x = (int) ((point.x() - rect.x()) * xScale);
        int y = (int) ((point.y() - rect.y()) * yScale);
        return image.pixelAt(x, y);
    }
}
