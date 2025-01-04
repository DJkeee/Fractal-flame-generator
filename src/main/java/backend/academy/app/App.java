package backend.academy.app;

import backend.academy.fractalimage.FractalImage;
import backend.academy.imagesaving.ImageSaver;
import backend.academy.postprocessing.GammaLogProcessor;
import backend.academy.postprocessing.ImageProcessor;
import backend.academy.render.Render;
import backend.academy.render.RenderLogic;
import backend.academy.render.ThreadRender;
import backend.academy.transformations.Transformation;

/**
 * Класс App представляет приложение для генерации и обработки фрактальных изображений.
 * Он использует настройки, предоставленные через объект AppSettings, для создания
 * фрактального изображения, его рендеринга и последующей обработки.
 */
public class App {
    private final AppSettings settings;

    /**
     * Конструктор класса App.
     *
     * @param settings настройки приложения, содержащие параметры для генерации фракталов
     */
    public App(AppSettings settings) {
        this.settings = settings;
    }

    /**
     * Запускает процесс генерации и обработки фрактального изображения.
     * Создает фрактальное изображение, настраивает логику рендеринга,
     * выполняет рендеринг, обрабатывает изображение и сохраняет его на диск.
     */
    public void start() {
        FractalImage fractalImage = FractalImage.create(settings.getWidth(), settings.getHeight());
        RenderLogic logic = setUpRenderLogic();
        Render render = new ThreadRender(fractalImage, logic);
        fractalImage = render.renderImage();
        ImageProcessor processor = new GammaLogProcessor();
        processor.process(fractalImage);
        ImageSaver.save(fractalImage, settings.getPath(), settings.getFormat());
    }

    /**
     * Настраивает логику рендеринга на основе параметров из настроек приложения.
     *
     * @return объект RenderLogic, содержащий параметры для рендеринга
     */
    private RenderLogic setUpRenderLogic() {
        return new RenderLogic(
            settings.getRect(),
            getTransformation(settings.getTransformation()),
            settings.getAffineTransformations(),
            settings.getIterations(),
            settings.getThreads());
    }

    /**
     * Получает объект Transformation на основе имени трансформации.
     *
     * @param name имя трансформации
     * @return соответствующий объект Transformation
     * @throws IllegalArgumentException если имя трансформации не распознано
     */
    @SuppressWarnings("ReturnCount")
    public Transformation getTransformation(String name) {
        switch (name.toLowerCase()) {
            case "disc":
                return settings.getDiscTransformation();
            case "exponential":
                return settings.getExponentialTransformation();
            case "diamond":
                return settings.getDiamondTransformation();
            case "sinusoidal":
                return settings.getSinusoidalTransformation();
            case "ex":
                return settings.getExTransformation();
            case "heart":
            default:
                return settings.getHeartTransformation();
        }
    }
}


