package backend.academy.render;

import backend.academy.fractalimage.FractalImage;

/**
 * Класс ThreadRender реализует интерфейс Render и отвечает за рендеринг фрактальных изображений
 * с использованием логики рендеринга, предоставляемой классом RenderLogic. В зависимости от
 * количества потоков, указанных в RenderLogic, он может выполнять рендеринг в одном потоке или
 * многопоточно.
 */
public class ThreadRender implements Render {
    private final RenderLogic logic;
    private final FractalImage image;

    /**
     * Конструктор класса ThreadRender.
     *
     * @param image изображение, которое будет рендериться
     * @param logic логика рендеринга, используемая для выполнения рендеринга
     */
    public ThreadRender(FractalImage image, RenderLogic logic) {
        this.logic = logic;
        this.image = image;
    }

    /**
     * Выполняет рендеринг изображения. В зависимости от количества потоков в логике рендеринга,
     * метод вызывает либо однопоточный, либо многопоточный рендеринг.
     *
     * @return сгенерированное фрактальное изображение
     */
    @Override
    public FractalImage renderImage() {
        if (logic.getThreads() == 1) {
            logic.singleThreadRender(image);
            return image;
        } else {
            logic.multiThreadRender(image);
            return image;
        }
    }
}

