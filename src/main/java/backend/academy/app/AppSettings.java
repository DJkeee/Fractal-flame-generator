package backend.academy.app;

import backend.academy.imagesaving.ImageFormat;
import backend.academy.primitives.Rect;
import backend.academy.transformations.AffineTransformation;
import backend.academy.transformations.DiamondTransformation;
import backend.academy.transformations.DiscTransformation;
import backend.academy.transformations.ExTransformation;
import backend.academy.transformations.ExponentialTransformation;
import backend.academy.transformations.HeartTransformation;
import backend.academy.transformations.SinusoidalTransformation;
import backend.academy.transformations.TransformationsListCreator;
import com.beust.jcommander.Parameter;
import java.nio.file.Path;
import java.util.List;

/**
 * Класс AppSettings содержит настройки приложения для обработки изображений.
 * Он использует аннотации для параметров командной строки, которые необходимы
 * для задания различных параметров обработки изображений.
 */
public class AppSettings {

    /**
     * Ширина изображения.
     */
    @Parameter(names = "--width", required = true, description = "Ширина изображения")
    private int width;

    /**
     * Высота изображения.
     */
    @Parameter(names = "--height", required = true, description = "Высота изображения")
    private int height;

    /**
     * Количество итераций обработки.
     */
    @Parameter(names = "--iterations", required = true, description = "Количество итераций")
    private int iterations;

    /**
     * Количество потоков для обработки (по умолчанию 1).
     */
    @Parameter(names = "--threads", description = "Количество потоков для использования (по умолчанию 1)")
    private int threads = 1;

    /**
     * Тип трансформации: диск, экспоненциальная, ex, синусоидальная, ромб, сердце (значение по умолчанию).
     */
    @Parameter(names = "--transformation", required = false,
        description = "Тип трансформации: disc, exponential, ex, sinusoidal, diamond, heart (значение по умолчанию)")
    private String transformation;

    /**
     * Путь для сохранения изображения.
     */
    @Parameter(names = "--path", required = true, description = "Путь для сохранения изображения")
    private Path path;

    /**
     * Формат изображения (по умолчанию PNG).
     */
    @Parameter(names = "--format", required = false, description = "Формат изображения")
    private ImageFormat format = ImageFormat.PNG;

    // Константы и статические поля для трансформаций
    private static final int AFFINE_TRANSFORM_NUMBER = 10;
    private static final List<AffineTransformation> AFFINE_TRANSFORMS =
        TransformationsListCreator.createAffineTransformationsList(AFFINE_TRANSFORM_NUMBER);
    private static final Rect RECT = new Rect(0, 0, 1, 1);
    private static final DiscTransformation DISC = new DiscTransformation(RECT);
    private static final ExponentialTransformation EXPONENTIAL = new ExponentialTransformation();
    private static final HeartTransformation HEART = new HeartTransformation(RECT);
    private static final DiamondTransformation DIAMOND = new DiamondTransformation(RECT);
    private static final SinusoidalTransformation SINUSOIDAL = new SinusoidalTransformation(RECT);
    private static final ExTransformation EX = new ExTransformation();

    public ExTransformation getExTransformation() {
        return EX;
    }

    public DiamondTransformation getDiamondTransformation() {
        return DIAMOND;
    }

    public SinusoidalTransformation getSinusoidalTransformation() {
        return SINUSOIDAL;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getIterations() {
        return iterations;
    }

    public int getThreads() {
        return threads;
    }

    public String getTransformation() {
        return transformation;
    }

    public List<AffineTransformation> getAffineTransformations() {
        return AFFINE_TRANSFORMS;
    }

    public DiscTransformation getDiscTransformation() {
        return DISC;
    }

    public ExponentialTransformation getExponentialTransformation() {
        return EXPONENTIAL;
    }

    public HeartTransformation getHeartTransformation() {
        return HEART;
    }

    public Rect getRect() {
        return RECT;
    }

    public Path getPath() {
        return path;
    }

    public ImageFormat getFormat() {
        return format;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public void setTransformation(String transformation) {
        this.transformation = transformation;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public void setFormat(ImageFormat format) {
        this.format = format;
    }

}



