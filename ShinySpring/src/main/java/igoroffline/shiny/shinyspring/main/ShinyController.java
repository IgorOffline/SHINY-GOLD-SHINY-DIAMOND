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
        Data.getData().put(gold, new ShinyData(new Gold(Magic.DEFAULT_GOLD_AMOUNT), ShinyType.GOLD));
        Data.getData().put(semaphoreColor, new ShinyData(SemaphoreColor.YELLOW, ShinyType.SEMAPHORE_COLOR));
    }

    @GetMapping
    public Gold getGold() {
        log.info("<GET::GOLD>");
        final var currentGold = getCurrentGold();
        log.info("currentGold= {}", currentGold);
        return currentGold;
    }

    private Gold getCurrentGold() {
        final var currentGoldRaw = Data.getData().get(gold);
        return Data.getGold(currentGoldRaw);
    }

    private Gold decreaseCurrentGold() {
        final var currentGoldRaw = Data.getData().get(gold);
        final var currentGold = Data.getGold(currentGoldRaw);
        var decreased = currentGold.value() - Magic.DEFAULT_GOLD_AMOUNT;
        if (decreased < Magic.LOWEST_POSSIBLE_GOLD_AMOUNT) {
            decreased = Magic.LOWEST_POSSIBLE_GOLD_AMOUNT;
        }
        final var newGold = new Gold(decreased);
        Data.getData().put(gold, new ShinyData(newGold, ShinyType.GOLD));
        return newGold;
    }

    @PostMapping("/double-gold")
    public Gold postDoubleGold() {
        log.info("<POST::DOUBLE-GOLD>");
        final var currentGoldRaw = Data.getData().get(gold);
        final var currentGold = Data.getGold(currentGoldRaw);
        final var doubleGold = currentGold.value() * 2;
        final var newGold = new Gold(doubleGold);
        Data.getData().put(gold, new ShinyData(newGold, ShinyType.GOLD));
        log.info("newGold= {}", newGold);
        return newGold;
    }

    @GetMapping("/semaphore-color")
    public SemaphoreColorString getSemaphoreColor() {
        log.info("<GET::SEMAPHORE-COLOR>");
        final var newSemaphoreColor = SemaphoreColor.getSwapYellowToOther(random);
        final var newSemaphoreColorString = newSemaphoreColor == SemaphoreColor.RED ? "red" : "green";
        log.info("newSemaphoreColorString= {}", newSemaphoreColorString);
        return new SemaphoreColorString(newSemaphoreColorString);
    }

    @PostMapping("/bet-red")
    public SemaphoreColorString postBetRed() {
        log.info("<POST::BET-RED>");
        final var currentGold = getCurrentGold();
        if (currentGold.value() < Magic.DEFAULT_GOLD_AMOUNT) {
            return new SemaphoreColorString(Magic.NOT_ENOUGH_GOLD);
        }
        decreaseCurrentGold();
        final var newSemaphoreColor = SemaphoreColor.getSwapYellowToOther(random);
        final var newSemaphoreColorString = newSemaphoreColor == SemaphoreColor.RED ? "red" : "green";
        log.info("newSemaphoreColorString= {}", newSemaphoreColorString);
        return new SemaphoreColorString(newSemaphoreColorString);
    }

    @PostMapping("/bet-green")
    public SemaphoreColorString postBetGreen() {
        log.info("<POST::BET-GREEN>");
        final var currentGold = getCurrentGold();
        if (currentGold.value() < Magic.DEFAULT_GOLD_AMOUNT) {
            return new SemaphoreColorString(Magic.NOT_ENOUGH_GOLD);
        }
        decreaseCurrentGold();
        final var newSemaphoreColor = SemaphoreColor.getSwapYellowToOther(random);
        final var newSemaphoreColorString = newSemaphoreColor == SemaphoreColor.RED ? "red" : "green";
        log.info("newSemaphoreColorString= {}", newSemaphoreColorString);
        return new SemaphoreColorString(newSemaphoreColorString);
    }

    @PostMapping("/reset")
    private void reset() {
        log.info("<POST::RESET>");
        init();
    }
}
