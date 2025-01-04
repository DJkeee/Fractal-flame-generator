import backend.academy.app.App;
import backend.academy.app.AppSettings;
import backend.academy.imagesaving.ImageFormat;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {
    private AppSettings settings;
    private long startTime; // Переменная для хранения времени начала теста

    @BeforeEach
    public void setUp() {
        settings = new AppSettings();
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setPath(Paths.get("output/flame.png"));
        settings.setFormat(ImageFormat.PNG);
        settings.setIterations(10000000);
        settings.setThreads(8);
        settings.setTransformation("heart");

        startTime = System.nanoTime();
    }

    @AfterEach
    public void tearDown() {
        long endTime = System.nanoTime();
        long totalTestTime = endTime - startTime;
        System.out.println("Total test execution time: " + totalTestTime + " ns");
    }

    @Disabled("запуск через mvn тест работает с 1 ядром,а не всеми доступными")
    @Test
    public void testRenderTimeComparison() {
        long singleThreadedTime = measureRenderTime(8, 1000000);
        long multiThreadedTime = measureRenderTime(1, 1000000);

        assertTrue(multiThreadedTime < singleThreadedTime,
            "Multi-threaded rendering should be faster than single-threaded for 1M iterations");

    }

    private long measureRenderTime(int threads, int iterations) {
        settings.setThreads(threads);
        settings.setIterations(iterations);

        App app = new App(settings);

        long startTime = System.nanoTime();
        app.start();
        long endTime = System.nanoTime();

        return endTime - startTime;
    }
}

