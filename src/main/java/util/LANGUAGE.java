package util;

public enum LANGUAGE {
    RU("ru"),
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
