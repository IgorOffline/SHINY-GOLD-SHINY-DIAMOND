package igoroffline.shiny.shinyspring.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = "${shiny.path.frontend-base}")
@RestController
public class ShinyController {

    @GetMapping
    public Gold getGold() {
        final var gold = new Gold(50);
        log.info("getGold= {}", gold);
        return gold;
    }

    @GetMapping("/double-gold")
    public Gold getDoubleGold() {
        final var gold = new Gold(100);
        log.info("getDoubleGold= {}", gold);
        return gold;
    }
}
