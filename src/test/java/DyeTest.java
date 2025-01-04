import backend.academy.primitives.Dye;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DyeTest {
    private Dye dye;

    @BeforeEach
    public void setUp() {
        dye = new Dye(100, 150, 200);
    }

    @Test
    public void testGetRed() {
        assertEquals(100, dye.getRed());
    }

    @Test
    public void testGetGreen() {
        assertEquals(150, dye.getGreen());
    }

    @Test
    public void testGetBlue() {
        assertEquals(200, dye.getBlue());
    }

    @Test
    public void testGetRGB() {
        int expectedRGB = (0xFF << 24) | (100 << 16) | (150 << 8) | 200;
        assertEquals(expectedRGB, dye.getRGB());
    }

    @Test
    public void testMixDye() {
        Dye otherDye = new Dye(200, 50, 100);
        dye.mixDye(otherDye);

        assertEquals((100 + 200) / 2, dye.getRed());
        assertEquals((150 + 50) / 2, dye.getGreen());
        assertEquals((200 + 100) / 2, dye.getBlue());
    }

    @Test
    public void testRandomDyeCreator() {
        Dye randomDye = Dye.randomDyeCreator();

        assertTrue(randomDye.getRed() >= 0 && randomDye.getRed() <= 255);
        assertTrue(randomDye.getGreen() >= 0 && randomDye.getGreen() <= 255);
        assertTrue(randomDye.getBlue() >= 0 && randomDye.getBlue() <= 255);
    }
}

