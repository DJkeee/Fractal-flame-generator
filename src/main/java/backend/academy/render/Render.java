package backend.academy.render;

import backend.academy.fractalimage.FractalImage;

/**
 * Функциональный интерфейс Render определяет контракт для рендеринга фрактальных изображений.
 * Интерфейс содержит один абстрактный метод, который должен быть реализован для выполнения
 * процесса рендеринга и возвращения сгенерированного изображения.
 */
@FunctionalInterface
public interface Render {

    /**
     * Выполняет рендеринг фрактального изображения.
     *
     * @return сгенерированное фрактальное изображение
     */
    FractalImage renderImage();
}

