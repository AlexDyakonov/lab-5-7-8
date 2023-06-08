package ru.home.app.ui;

/**
 * The type Console colors.
 */
public class ConsoleColors {
    /**
     * The constant RESET.
     */
    public static final String RESET = "\033[0m";
    /**
     * The constant RED.
     */
    public static final String RED = "\033[0;31m";
    /**
     * The constant GREEN.
     */
    public static final String GREEN = "\033[0;32m";
    /**
     * The constant RED_BRIGHT.
     */
    public static final String RED_BRIGHT = "\033[0;91m";
    /**
     * The constant YELLOW_BRIGHT.
     */
    public static final String YELLOW_BRIGHT = "\033[0;93m";
    /**
     * The constant WHITE_BRIGHT.
     */
    public static final String WHITE_BRIGHT = "\033[0;97m";

    /**
     * Success string.
     *
     * @param line the line
     * @return the string
     */
    public static final String success(String line) {
        return (GREEN + line + RESET);
    }

    /**
     * Unsuccess string.
     *
     * @param line the line
     * @return the string
     */
    public static final String unsuccess(String line) {
        return (RED_BRIGHT + line + RESET);
    }

    /**
     * Error string.
     *
     * @param line the line
     * @return the string
     */
    public static final String error(String line) {
        return (RED + line + RESET);
    }

    /**
     * White str string.
     *
     * @param line the line
     * @return the string
     */
    public static final String whiteStr(String line) {
        return (WHITE_BRIGHT + line + RESET);
    }

    /**
     * Warning string.
     *
     * @param line the line
     * @return the string
     */
    public static final String warning(String line) {
        return (YELLOW_BRIGHT + line + RESET);
    }


}