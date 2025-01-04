package backend.academy.postprocessing;

import backend.academy.fractalimage.FractalImage;
import backend.academy.primitives.Dye;
import backend.academy.primitives.Pixel;
import java.util.Arrays;
import java.util.Objects;
import static backend.academy.primitives.Dye.DYE_RANGE;

/**
 * Класс GammaLogProcessor реализует интерфейс ImageProcessor и предназначен для
 * обработки изображений с использованием гамма-коррекции и логарифмической
 * нормализации цвета пикселей. Этот класс применяет гамма-коррекцию к цветам
 * пикселей на основе их количества попаданий (hit count) и затем осуществляет
 * сглаживание цветов, чтобы улучшить визуальное восприятие изображения.
 */
public class GammaLogProcessor implements ImageProcessor {

    /**
     * Значение гамма, используемое для коррекции цвета (по умолчанию 2.2).
     */
    @SuppressWarnings("MagicNumber")
    private double gamma = 2.2;

    /**
     * Конструктор по умолчанию для создания экземпляра GammaLogProcessor.
     */
    public GammaLogProcessor() {
    }

    /**
     * Обрабатывает заданное фрактальное изображение, применяя логарифмическую
     * коррекцию цвета к каждому пикселю на основе его количества попаданий.
     *
     * @param image фрактальное изображение, которое будет обработано
     */
    @Override
    public void process(FractalImage image) {
        int maxHitCount = findMaxHitCount(image);
        if (maxHitCount == 0) {
            return;
        }

        double logMax = Math.log10(maxHitCount);

        for (Pixel pixel : image.data()) {
            if (pixel == null || pixel.getHitCount() == 0) {
                continue;
            }
            int red = logCorrection(pixel.getRed(), pixel.getHitCount(), logMax);
            int green = logCorrection(pixel.getGreen(), pixel.getHitCount(), logMax);
            int blue = logCorrection(pixel.getBlue(), pixel.getHitCount(), logMax);
            Dye dye = new Dye(red, green, blue);
            pixel.setDye(dye);
        }

        smoothImage(image.data());
    }

    /**
     * Сглаживает цвета пикселей, используя средние значения цветов соседних пикселей.
     *
     * @param pixels массив пикселей
     */
    private void smoothImage(Pixel[] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            if (pixels[i] != null) {
                Dye smoothedDye = smoothColors(pixels, i);
                pixels[i].setDye(smoothedDye);
            }
        }
    }

    /**
     * Находит максимальное количество попаданий среди всех пикселей в изображении.
     *
     * @param image фрактальное изображение, из которого извлекаются данные о пикселях
     * @return максимальное количество попаданий среди пикселей
     */
    private int findMaxHitCount(FractalImage image) {
        return Arrays.stream(image.data())
            .filter(Objects::nonNull)
            .mapToInt(Pixel::getHitCount)
            .max()
            .orElse(0);
    }

    /**
     * Применяет логарифмическую коррекцию к значению цвета на основе количества попаданий.
     *
     * @param color    значение цвета (красный, зеленый или синий)
     * @param hitCount количество попаданий для данного пикселя
     * @param logMax   логарифмическое значение максимального количества попаданий
     * @return откорректированное значение цвета
     */
    private int logCorrection(int color, int hitCount, double logMax) {
        if (hitCount <= 1) {
            return color;
        }
        double logValue = Math.log10(hitCount);
        return (int) Math.max(0, Math.min(DYE_RANGE, Math.round(color * Math.pow(logValue / logMax, 1 / gamma))));
    }

    /**
     * Сглаживает цвета текущего пикселя на основе цветов его соседей.
     *
     * @param pixels массив пикселей
     * @param index  индекс текущего пикселя
     * @return объект Dye, представляющий средний цвет соседей
     */
    private Dye smoothColors(Pixel[] pixels, int index) {
        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;
        int count = 0;

        for (int offset = -1; offset <= 1; offset++) {
            int neighborIndex = index + offset;

            if (neighborIndex >= 0 && neighborIndex < pixels.length && pixels[neighborIndex] != null) {
                totalRed += pixels[neighborIndex].getDye().getRed();
                totalGreen += pixels[neighborIndex].getDye().getGreen();
                totalBlue += pixels[neighborIndex].getDye().getBlue();
                count++;
            }
        }

        if (count > 0) {
            return new Dye(totalRed / count, totalGreen / count, totalBlue / count);
        } else {
            return new Dye(0, 0, 0);
        }
    }
}
