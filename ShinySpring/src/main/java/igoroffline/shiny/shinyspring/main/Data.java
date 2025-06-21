package igoroffline.shiny.shinyspring.main;

import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;

public class Data {

    @Getter
    private static final ConcurrentHashMap<String, Gold> data = new ConcurrentHashMap<>();
}
