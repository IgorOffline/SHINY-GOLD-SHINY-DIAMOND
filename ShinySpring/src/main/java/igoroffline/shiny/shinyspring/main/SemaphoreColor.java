package igoroffline.shiny.shinyspring.main;

import java.util.Random;

public enum SemaphoreColor {
    RED,
    YELLOW,
    GREEN;

    public static SemaphoreColor getSwapYellowToOther(Random random) {
        return random.nextBoolean() ? SemaphoreColor.GREEN : SemaphoreColor.RED;
    }
}
