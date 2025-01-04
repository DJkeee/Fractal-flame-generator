package backend.academy.fractalimage;

import backend.academy.primitives.Dye;
import backend.academy.primitives.Pixel;

/**
 * Класс FractalImage представляет собой фрактальное изображение, состоящее из массива
 * пикселей. Он хранит информацию о ширине и высоте изображения, а также предоставляет
 * методы для создания изображения и доступа к пикселям по координатам.
 *
 * @param data   массив пикселей, представляющий изображение
 * @param width  ширина изображения в пикселях
 * @param height высота изображения в пикселях
 */
@SuppressWarnings("MagicNumber")
public record FractalImage(Pixel[] data, int width, int height) {

    /**
     * Создает новое фрактальное изображение заданной ширины и высоты с черными пикселями.
     *
     * @param width  ширина создаваемого изображения
     * @param height высота создаваемого изображения
     * @return новое фрактальное изображение с заданными размерами
     */
    public static FractalImage create(int width, int height) {
        Pixel[] pixels = new Pixel[width * height];
        for (int i = 0; i < width * height; i++) {
            Dye dye = new Dye(255, 255, 255);
            pixels[i] = new Pixel(dye, 0);
        }
        return new FractalImage(pixels, width, height);
    }

    /**
     * Проверяет, содержится ли заданная точка (x, y) в пределах изображения.
     *
     * @param x координата по оси X
     * @param y координата по оси Y
     * @return true, если точка находится внутри изображения; иначе false
     */
    public boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /**
     * Возвращает пиксель по заданным координатам (x, y) в изображении.
     *
     * @param x координата по оси X
     * @param y координата по оси Y
     * @return пиксель в заданной точке или null, если координаты выходят за пределы изображения
     */
    public Pixel pixelAt(int x, int y) {
        if (!contains(x, y)) {
            return null;
        }
        return data[x + y * width];
    }
}

