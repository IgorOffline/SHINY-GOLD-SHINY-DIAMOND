package igoroffline.shiny.shinyspring.main;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class SemaphoreTest {

    private final Semaphore semaphore = new Semaphore();

    @Test
    void testSemaphore() {
        final var indices = new HashSet<Integer>();
        final var semaphoreColors = new HashSet<SemaphoreColor>();
        final var random = new SecureRandom();
        for (int i = 0; i < 100; i++) {
            final var index = semaphore.getRandomSemaphoreColorIndex(random);
            indices.add(index);
            final var semaphoreColor = SemaphoreColor.getSemaphoreColor(index);
            semaphoreColors.add(semaphoreColor);
        }

        assertThat(indices).hasSize(3).contains(0, 1, 2).doesNotContain(-1, 3);
        assertThat(semaphoreColors).hasSize(3).contains(SemaphoreColor.RED, SemaphoreColor.YELLOW, SemaphoreColor.GREEN);
    }
}
