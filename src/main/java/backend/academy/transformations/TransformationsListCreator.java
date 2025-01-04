package backend.academy.transformations;

import java.util.ArrayList;
import java.util.List;

public class TransformationsListCreator {

    private TransformationsListCreator() {
    }

    public static List<AffineTransformation> createAffineTransformationsList(int numberOfTransformations) {
        List<AffineTransformation> transformations = new ArrayList<>(numberOfTransformations);
        for (int i = 0; i < numberOfTransformations; i++) {
            AffineCoefficients coefs = AffineCoefficients.generateAffine();
            AffineTransformation affineTransformation = new AffineTransformation(coefs);
            transformations.add(affineTransformation);
        }
        return transformations;
    }
}
