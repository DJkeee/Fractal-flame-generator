package backend.academy.imagesaving;

import backend.academy.fractalimage.FractalImage;
import backend.academy.primitives.Pixel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import lombok.SneakyThrows;

/**
 * Класс ImageSaver предоставляет методы для сохранения фрактальных изображений
 * в файлы различных форматов.
 */
public final class ImageSaver {
    static final Logger LOGGER = Logger.getLogger(ImageSaver.class.getName());

    private ImageSaver() {
    }

    /**
     * Сохраняет заданное фрактальное изображение в файл с указанным именем и форматом.
     *
     * @param image    фрактальное изображение, которое нужно сохранить
     * @param filename путь к файлу, в который будет сохранено изображение
     * @param format   формат изображения, в который будет сохранено изображение
     * @throws IOException если произошла ошибка ввода-вывода при сохранении файла
     */
    @SneakyThrows
    public static void save(FractalImage image, Path filename, ImageFormat format) {
        BufferedImage renderedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        renderedImage.setRGB(0, 0, image.width(), image.height(), convertRGBToIntArray(image), 0, image.width());
        try (OutputStream out = Files.newOutputStream(filename)) {
            ImageIO.write(renderedImage, format.getFormat(), out);
            LOGGER.info("Успешное сохранение");
        } catch (IOException ex) {
            LOGGER.severe(ex.getMessage());
            LOGGER.severe("Ошибка при сохранении файла");
        }
    }

    /**
     * Преобразует данные фрактального изображения в массив целых чисел,
     * представляющий цвет пикселей в формате RGB.
     *
     * @param image фрактальное изображение, данные которого нужно преобразовать
     * @return массив целых чисел, представляющий цвета пикселей изображения
     */
    public static int[] convertRGBToIntArray(FractalImage image) {
        int[] pix = new int[image.data().length];
        for (int i = 0; i < pix.length; i++) {
            Pixel pixel = image.data()[i];
            pix[i] = pixel.getRgb();
        }
        return pix;
    }
}
