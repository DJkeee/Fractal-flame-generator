import backend.academy.primitives.Dye;
import backend.academy.primitives.Pixel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PixelTest {
    private Dye mockDye1;
    private Dye mockDye2;
    private Pixel pixel;

    @BeforeEach
    public void setUp() {
        // Создаем мок-объекты для Dye
        mockDye1 = mock(Dye.class);
        mockDye2 = mock(Dye.class);

        // Настраиваем поведение мок-объектов
        when(mockDye1.getRed()).thenReturn(255);
        when(mockDye1.getGreen()).thenReturn(0);
        when(mockDye1.getBlue()).thenReturn(0);
        when(mockDye1.getRGB()).thenReturn(0xFF0000); // Красный цвет

        when(mockDye2.getRed()).thenReturn(0);
        when(mockDye2.getGreen()).thenReturn(0);
        when(mockDye2.getBlue()).thenReturn(255);
        when(mockDye2.getRGB()).thenReturn(0x0000FF); // Синий цвет

        // Создаем объект Pixel с первым красителем
        pixel = new Pixel(mockDye1);
    }

    @Test
    public void testInitialValues() {
        assertEquals(255, pixel.getRed());
        assertEquals(0, pixel.getGreen());
        assertEquals(0, pixel.getBlue());
        assertEquals(0, pixel.getHitCount());
        assertEquals(0xFF0000, pixel.getRgb());
    }

    @Test
    public void testUpdateDyeAfterHintFirstTime() {
        pixel.updateDyeAfterHint(mockDye2);

        assertEquals(mockDye2, pixel.getDye());
        assertEquals(1, pixel.getHitCount());
    }

    @Test
    public void testSetDye() {
        pixel.setDye(mockDye2);

        assertEquals(mockDye2, pixel.getDye());
        assertEquals(0, pixel.getHitCount()); // hitCount должен остаться 0
    }
}

