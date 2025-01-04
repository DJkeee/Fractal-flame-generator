package backend.academy.transformations;

import backend.academy.primitives.Dye;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.concurrent.ThreadLocalRandom;

public record AffineCoefficients(double coefA, double coefB, double coefC,
                                 double coefD, double coefE, double coefF, Dye dye) {

    public static AffineCoefficients generateAffine() {
        double a = getRandCoef();
        double b = getRandCoef();
        double c = getRandCoef();
        double d = getRandCoef();
        double e = getRandCoef();
        double f = getRandCoef();

        while (!isValidCoefficient(a, b, d, e)) {
            a = getRandCoef();
            b = getRandCoef();
            c = getRandCoef();
            d = getRandCoef();
            e = getRandCoef();
            f = getRandCoef();
        }
        Dye dye = Dye.randomDyeCreator();
        return new AffineCoefficients(a, b, c, d, e, f, dye);

    }

    @SuppressFBWarnings(value = "PREDICTABLE_RANDOM", justification = "multithread random")
    public static double getRandCoef() {
        return ThreadLocalRandom.current().nextDouble(-1, 1);
    }

    public static boolean isValidCoefficient(
        double coefA,
        double coefB,
        double coefD,
        double coefE
    ) {
        return (coefA * coefA + coefB * coefB < 1)
            && (coefB * coefB + coefE * coefE < 1)
            && (coefA * coefA + coefB * coefB + coefD * coefD + coefE * coefE
            < 1 + (coefE * coefA - coefB * coefD) * (coefE * coefA - coefB * coefD));
    }
}
