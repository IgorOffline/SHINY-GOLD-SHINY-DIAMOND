package igoroffline.shiny.shinyspring.main;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class SemaphoreTest {

    @Test
    void testSwapYellowToOther() {
        final var semaphoreColors = new HashSet<SemaphoreColor>();
        final var random = new SecureRandom();
        for (int i = 0; i < 100; i++) {
            final var semaphoreColor = SemaphoreColor.getSwapYellowToOther(random);
            semaphoreColors.add(semaphoreColor);
        }

        assertThat(semaphoreColors).hasSize(2).contains(SemaphoreColor.RED, SemaphoreColor.GREEN).doesNotContain(SemaphoreColor.YELLOW);
    }
}
