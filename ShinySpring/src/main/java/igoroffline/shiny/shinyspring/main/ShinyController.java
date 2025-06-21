package igoroffline.shiny.shinyspring.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ShinyController {

    @GetMapping
    @CrossOrigin(origins = "${shiny.path.frontend-base}")
    public Gold getGold() {
        final var gold = new Gold(50);
        log.info("getGold= {}", gold);
        return gold;
    }
}
