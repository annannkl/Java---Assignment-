public class ColoredUI {
    // ANSI escape code constants for text colors
    private String RESET = "\u001B[0m"; // Reset text color
    private String blue = "\u001B[34m"; // Set text color to blue
    private String magenta = "\u001B[35m"; // Set text color to magenta
    private String red = "\u001B[31m"; // Set text color to red
    private String green = "\u001B[32m"; // Set text color to green
    private String yellow = "\u001B[33m"; // Set text color to yellow
    private String cyan = "\u001B[36m" ; // Set text color to cyan
    private String purple = "\u001B[35m"; // Set text color to purple

    public ColoredUI() {
        this.RESET = RESET;
        this.blue = blue;
        this.magenta = magenta;
        this.red = red;
        this.green = green;
        this.yellow = yellow;
        this.cyan = cyan;
        this.purple = purple;
    }

    public String getRESET() {
        return RESET;
    }

    public String getGreen() {
        return green;
    }

    public String getRed() {
        return red;
    }

    public String getMagenta() {
        return magenta;
    }

    public String getBlue() {
        return blue;
    }

    public String getYellow() {
        return yellow;
    }

    public String getCyan() {
        return cyan;
    }

    public String getPurple() {
        return purple;
    }
}
