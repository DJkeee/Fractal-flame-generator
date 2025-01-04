package backend.academy.postprocessing;

import backend.academy.fractalimage.FractalImage;

/**
 * Функциональный интерфейс ImageProcessor определяет контракт для обработки изображений.
 * Интерфейс содержит один абстрактный метод, который должен быть реализован для выполнения
 * обработки фрактального изображения.
 */
@FunctionalInterface
public interface ImageProcessor {

    /**
     * Обрабатывает заданное фрактальное изображение.
     *
     * @param image фрактальное изображение, которое необходимо обработать
     */
    void process(FractalImage image);
}

