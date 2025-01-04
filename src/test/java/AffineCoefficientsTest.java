import backend.academy.primitives.Dye;
import backend.academy.transformations.AffineCoefficients;
import org.junit.jupiter.api.Test;
import static backend.academy.transformations.AffineCoefficients.isValidCoefficient;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AffineCoefficientsTest {

    @Test
    public void testGenerateAffine() {
        AffineCoefficients coefficients = AffineCoefficients.generateAffine();

        assertNotNull(coefficients);
        assertNotNull(coefficients.dye());

        assertTrue(coefficients.coefA() >= -1 && coefficients.coefA() <= 1);
        assertTrue(coefficients.coefB() >= -1 && coefficients.coefB() <= 1);
        assertTrue(coefficients.coefC() >= -1 && coefficients.coefC() <= 1);
        assertTrue(coefficients.coefD() >= -1 && coefficients.coefD() <= 1);
        assertTrue(coefficients.coefE() >= -1 && coefficients.coefE() <= 1);
        assertTrue(coefficients.coefF() >= -1 && coefficients.coefF() <= 1);

        assertTrue(isValidCoefficient(coefficients.coefA(), coefficients.coefB(),
            coefficients.coefD(), coefficients.coefE()));
    }

    @Test
    public void testIsValidCoefficient() {
        assertTrue(isValidCoefficient(0.5, 0.5, 0.5, 0.47));
        assertTrue(isValidCoefficient(0.0, 0.0, 0.0, 0.0));

        assertFalse(isValidCoefficient(1.0, 0.0, 0.0, 0.0));
        assertFalse(isValidCoefficient(0.5, 0.5, 0.5, 0.6));
    }

    @Test
    public void testRandomDyeCreation() {
        AffineCoefficients coefficients = AffineCoefficients.generateAffine();

        Dye dye = coefficients.dye();
        assertNotNull(dye);
        assertTrue(dye.getRed() >= 0 && dye.getRed() <= 255);
        assertTrue(dye.getGreen() >= 0 && dye.getGreen() <= 255);
        assertTrue(dye.getBlue() >= 0 && dye.getBlue() <= 255);
    }
}
