package igoroffline.shiny.shinyspring.main;

import jakarta.annotation.PostConstruct;
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

    @PostConstruct
    public void init() {
        Data.getData().put(gold, new Gold(50));
    }

    @GetMapping
    public Gold getGold() {
        log.info("getGold");
        final var currentGold = new Gold(Data.getData().get(gold).value());
        log.info("currentGold= {}", currentGold);
        return currentGold;
    }

    @PostMapping("/double-gold")
    public Gold postDoubleGold() {
        log.info("postDoubleGold");
        final var data = Data.getData();
        final var doubleGold = data.get(gold).value() * 2;
        final var newGold = new Gold(doubleGold);
        data.put(gold, newGold);
        log.info("newGold= {}", newGold);
        return newGold;
    }
}
