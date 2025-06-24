package igoroffline.shiny.shinyspring.main;

import java.util.Random;

public class Semaphore {

    public int getRandomSemaphoreColorIndex(Random random) {
        return random.nextInt(SemaphoreColor.values().length);
    }
}
