package App;

/**
 * Enum odpowiadający za kolory. Używany w klasach GameHandler i Instruction.
 */
public enum ANSI_Colours {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),
    PINK("\u001B[35;1m"),
    ORANGE("\u001B[38;2;255;165;1m"),
    BROWN("\u001b[38;5;94m");

    private final String colour;

    ANSI_Colours(String colour){
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }
}
