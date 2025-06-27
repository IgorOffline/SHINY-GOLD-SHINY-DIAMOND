package igoroffline.shiny.shinyspring.main;

import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;

public class Data {

    @Getter
    private static final ConcurrentHashMap<String, ShinyData> data = new ConcurrentHashMap<>();

    public static Gold getGold(ShinyData data) {
        return (Gold) data.obj();
    }
}
