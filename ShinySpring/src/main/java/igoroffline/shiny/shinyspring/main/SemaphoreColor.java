package igoroffline.shiny.shinyspring.main;

public enum SemaphoreColor {
    RED, YELLOW, GREEN;

    public static SemaphoreColor getSemaphoreColor(int index) {
        return values()[index];
    }
}
