package backend.academy.transformations;

import backend.academy.primitives.Point;

public record AffineTransformation(AffineCoefficients affineCoefs) implements Transformation {

    @Override
    public Point apply(Point point) {
        AffineCoefficients affine = AffineCoefficients.generateAffine();
        double x = affine.coefA() + point.x() * affine.coefB() + point.y() * affine.coefC();
        double y = affine.coefD() + point.x() * affine.coefE() + point.y() * affine.coefF();
        return new Point(x, y);
    }
}
