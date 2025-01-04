package backend.academy.primitives;

public class Pixel {
    private Dye dye;
    private int hitCount;

    public Pixel(Dye dye, int hitCount) {
        this.dye = dye;
        this.hitCount = hitCount;
    }

    public Pixel(Dye dye) {
        this.dye = dye;
        this.hitCount = 0;
    }

    public void setDye(Dye dye) {
        this.dye = dye;
    }

    public int getRed() {
        return dye.getRed();
    }

    public int getGreen() {
        return dye.getGreen();
    }

    public int getBlue() {
        return dye.getBlue();
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getRgb() {
        return dye.getRGB();
    }

    public Dye getDye() {
        return dye;
    }

    public void updateDyeAfterHint(Dye dye) {
        if (hitCount == 0) {
            setDye(dye);
        } else {
            dye.mixDye(dye);
        }
        hitCount++;
    }
}
