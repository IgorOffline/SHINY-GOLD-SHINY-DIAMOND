package igoroffline.shiny.shinyspring.main;

import jakarta.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = "${shiny.path.frontend-base}")
@RestController
public class ShinyController {

    public static final String gold = "GOLD";
    public static final String semaphoreColor = "SEMAPHORE_COLOR";

    private final Random random;

    public ShinyController() {
        random = new SecureRandom();
    }

    public ShinyController(Random random) {
        this.random = random;
    }

    @PostConstruct
    public void init() {
        Data.getData().put(gold, new ShinyData(new Gold(50), ShinyType.GOLD));
        Data.getData().put(semaphoreColor, new ShinyData(SemaphoreColor.YELLOW, ShinyType.SEMAPHORE_COLOR));
    }

    @GetMapping
    public Gold getGold() {
        log.info("getGold");
        final var currentGoldRaw = Data.getData().get(gold);
        final var currentGold = Data.getGold(currentGoldRaw);
        log.info("currentGold= {}", currentGold);
        return currentGold;
    }

    @PostMapping("/double-gold")
    public Gold postDoubleGold() {
        log.info("postDoubleGold");
        final var currentGoldRaw = Data.getData().get(gold);
        final var currentGold = Data.getGold(currentGoldRaw);
        final var doubleGold = currentGold.value() * 2;
        final var newGold = new Gold(doubleGold);
        Data.getData().put(gold, new ShinyData(newGold, ShinyType.GOLD));
        log.info("newGold= {}", newGold);
        return newGold;
    }

    @GetMapping("/get-semaphore-color")
    public SemaphoreColorString getSemaphoreColor() {
        log.info("getSemaphoreColor");
        final var newSemaphoreColor = SemaphoreColor.getSwapYellowToOther(random);
        final var newSemaphoreColorString = newSemaphoreColor == SemaphoreColor.RED ? "red" : "green";
        log.info("newSemaphoreColorString= {}", newSemaphoreColorString);
        return new SemaphoreColorString(newSemaphoreColorString);
    }

    @PostMapping("/bet-red")
    public SemaphoreColorString postBetRed() {
        log.info("postBetRed");
        final var newSemaphoreColor = SemaphoreColor.getSwapYellowToOther(random);
        final var newSemaphoreColorString = newSemaphoreColor == SemaphoreColor.RED ? "red" : "green";
        log.info("newSemaphoreColorString= {}", newSemaphoreColorString);
        return new SemaphoreColorString(newSemaphoreColorString);
    }

    @PostMapping("/bet-green")
    public SemaphoreColorString postBetGreen() {
        log.info("postBetGreen");
        final var newSemaphoreColor = SemaphoreColor.getSwapYellowToOther(random);
        final var newSemaphoreColorString = newSemaphoreColor == SemaphoreColor.RED ? "red" : "green";
        log.info("newSemaphoreColorString= {}", newSemaphoreColorString);
        return new SemaphoreColorString(newSemaphoreColorString);
    }
}
