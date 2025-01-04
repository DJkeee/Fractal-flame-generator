package backend.academy.render;

import backend.academy.fractalimage.FractalImage;
import backend.academy.fractalimage.FractalImageLogic;
import backend.academy.primitives.Dye;
import backend.academy.primitives.Pixel;
import backend.academy.primitives.Point;
import backend.academy.primitives.Rect;
import backend.academy.transformations.AffineTransformation;
import backend.academy.transformations.Transformation;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

/**
 * Класс RenderLogic отвечает за логику рендеринга фрактальных изображений с использованием
 * заданных преобразований, реализуя логику однопоточной и
 * многопоточной версий. Он генерирует случайные точки в заданном
 * прямоугольнике и применяет к ним аффинные и другие трансформации.
 */
public class RenderLogic {
    private final Transformation transformation;
    private final int iterPerSample;
    private final Rect rect;
    private final int samples;
    private final int threads;
    private final int iterBeforeRenderSkip;
    private final List<AffineTransformation> affineTransformations;
    private static final Logger LOGGER = Logger.getLogger(RenderLogic.class.getName());

    /**
     * Конструктор класса RenderLogic.
     *
     * @param rect                  прямоугольник, в пределах которого будут генерироваться точки
     * @param transformation        трансформация, применяемая к точкам
     * @param affineTransformations список аффинных преобразований
     * @param iterations            общее количество итераций для рендеринга
     * @param threads               количество потоков для параллельного рендеринга
     */
    @SuppressWarnings("MagicNumber")
    public RenderLogic(
        Rect rect,
        Transformation transformation,
        List<AffineTransformation> affineTransformations,
        int iterations,
        int threads
    ) {
        this.transformation = transformation;
        this.samples = iterations / threads;
        this.threads = threads;
        this.iterPerSample = 300;
        this.iterBeforeRenderSkip = 25;
        this.rect = rect;
        this.affineTransformations = affineTransformations;
    }

    /**
     * Рендерит образцы фрактального изображения с использованием многопоточности.
     *
     * @param image изображение, которое будет рендериться
     */
    private void renderSamples(FractalImage image) {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<?>[] futures = new Future<?>[samples];

            for (int i = 0; i < samples; i++) {
                futures[i] = executor.submit(() -> renderSample(image));
            }

            for (Future<?> future : futures) {
                future.get();
            }
        } catch (Exception exception) {
            LOGGER.severe(exception.getMessage());
        }
    }

    /**
     * Рендерит один образец фрактального изображения,
     * применяя случайные набор афинных преобразований и одно произвольное.
     *
     * @param image изображение, которое будет рендериться
     */
    @SuppressFBWarnings(value = "PREDICTABLE_RANDOM", justification = "multithread random")
    private void renderSample(FractalImage image) {
        Point currentPoint = Rect.generateRandomPoint(rect);

        for (int step = iterBeforeRenderSkip; step < iterPerSample + iterBeforeRenderSkip; ++step) {
            int randAffineIndex = ThreadLocalRandom.current().nextInt(affineTransformations.size());
            AffineTransformation affine = affineTransformations.get(randAffineIndex);

            currentPoint = affine.apply(currentPoint);
            currentPoint = transformation.apply(currentPoint);
            if (step > iterBeforeRenderSkip) {
                processPoint(image, currentPoint, affine);
            }
        }
    }

    /**
     * Обрабатывает точку и обновляет цвет пикселя в изображении.
     *
     * @param image  изображение, в котором будет обновлен пиксель
     * @param point  точка, которая будет обработана
     * @param affine аффинное преобразование, использованное для обработки точки
     */
    private void processPoint(
        FractalImage image,
        Point point,
        AffineTransformation affine
    ) {
        Pixel pixel = FractalImageLogic.pixelScaleFromRectToImage(image, rect, point);
        if (pixel != null) {
            synchronized (pixel) {
                Dye dye = affine.affineCoefs().dye();
                pixel.updateDyeAfterHint(dye);
            }
        }
    }

    /**
     * Возвращает количество образцов для рендеринга.
     *
     * @return количество образцов
     */
    public int getSamples() {
        return samples;
    }

    /**
     * Возвращает количество потоков, используемых для рендеринга.
     *
     * @return количество потоков
     */
    public int getThreads() {
        return threads;
    }

    /**
     * Выполняет рендеринг изображения в одном потоке.
     *
     * @param image изображение, которое будет рендериться
     */
    public void singleThreadRender(FractalImage image) {
        for (int i = 0; i < getSamples(); i++) {
            renderSample(image);
        }
    }

    /**
     * Выполняет рендеринг изображения с использованием нескольких потоков.
     *
     * @param image изображение, которое будет рендериться
     */
    public void multiThreadRender(FractalImage image) {
        renderSamples(image);
    }
}


