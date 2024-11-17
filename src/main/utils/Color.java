package main.utils;

public enum Color {
    RED, BLUE, GREEN, YELLOW, PURPLE, BLACK, WHITE;

    public String getName() {
        String name = this.name().toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
