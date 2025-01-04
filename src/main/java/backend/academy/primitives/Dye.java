package backend.academy.primitives;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс Dye представляет цветовой краситель, который содержит значения красного,
 * зеленого и синего компонентов цвета. Этот класс предоставляет методы для создания
 * случайных красителей, смешивания цветов и получения значения цвета в формате RGB.
 */
public class Dye {
    private int red;
    private int green;
    private int blue;
    public static final int DYE_RANGE = 255;

    /**
     * Создает новый экземпляр красителя с заданными значениями компонентов цвета.
     *
     * @param red   значение красного компонента (0-255)
     * @param green значение зеленого компонента (0-255)
     * @param blue  значение синего компонента (0-255)
     */
    public Dye(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Возвращает значение красного компонента цвета.
     *
     * @return значение красного компонента (0-255)
     */
    public int getRed() {
        return red;
    }

    /**
     * Возвращает значение зеленого компонента цвета.
     *
     * @return значение зеленого компонента (0-255)
     */
    public int getGreen() {
        return green;
    }

    /**
     * Возвращает значение синего компонента цвета.
     *
     * @return значение синего компонента (0-255)
     */
    public int getBlue() {
        return blue;
    }

    /**
     * Создает случайный краситель с компонентами цвета, выбранными из диапазона [0, 255].
     *
     * @return новый экземпляр случайного красителя
     */
    @SuppressFBWarnings(value = "PREDICTABLE_RANDOM", justification = "multithread random")
    public static Dye randomDyeCreator() {
        return new Dye(ThreadLocalRandom.current().nextInt(DYE_RANGE),
            ThreadLocalRandom.current().nextInt(DYE_RANGE),
            ThreadLocalRandom.current().nextInt(DYE_RANGE));
    }

    /**
     * Смешивает текущий краситель с другим красителем, изменяя значения компонентов цвета
     * на средние значения.
     *
     * @param otherDye другой краситель, с которым будет произведено смешивание
     */
    public void mixDye(Dye otherDye) {
        red = (red + otherDye.getRed()) / 2;
        green = (green + otherDye.getGreen()) / 2;
        blue = (blue + otherDye.getBlue()) / 2;
    }

    /**
     * Возвращает значение цвета в формате RGB.
     *
     * @return значение цвета в формате RGB (целое число)
     */
    @SuppressWarnings("MagicNumber")
    public int getRGB() {
        return (0xFF << 24) | (red << 16) | (green << 8) | blue;
    }
}

