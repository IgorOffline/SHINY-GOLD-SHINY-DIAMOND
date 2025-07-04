package igoroffline.shiny.shinyspring.main;

import java.util.Random;

public enum SemaphoreColor {
    RED("red"),
    YELLOW("yellow"),
    GREEN("green");

    public final String name;

    SemaphoreColor(String name) {
        this.name = name;
    }

    public static SemaphoreColor getSwapYellowToOther(Random random) {
        return random.nextBoolean() ? SemaphoreColor.GREEN : SemaphoreColor.RED;
    }
}
