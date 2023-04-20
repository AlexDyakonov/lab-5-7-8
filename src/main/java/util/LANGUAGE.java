package util;

/**
 * The enum Language.
 */
public enum LANGUAGE {
    /**
     * Ru language.
     */
    RU("ru"),
    /**
     * En language.
     */
    EN("en");
    private final String lan;

    LANGUAGE(String lan) {
        this.lan = lan;
    }

    @Override
    public String toString() {
        return lan;
    }
}
