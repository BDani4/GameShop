package hu.nye.progkor.GameShop.model;

public enum GameType {

    FIRST_PERSON_SHOOTER("FPS"), // Belső nézetes, lövöldözős játék
    REAL_TIME_STRATEGY("RTS"),   // Valós időben játszódó stratégiai játék
    HORROR("H");                 // Félelmet és/vagy fóbiát keltő játék

    private final String value;

    GameType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
