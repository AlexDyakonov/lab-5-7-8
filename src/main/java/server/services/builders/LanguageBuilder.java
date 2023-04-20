package server.services.builders;

import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;

import static util.Message.getMessage;
import static util.Message.getWarning;

/**
 * The type Language builder.
 */
public class LanguageBuilder {
    /**
     * Gets language.
     *
     * @param language  the language
     * @param cmdReader the cmd reader
     * @return the language
     */
    public static LANGUAGE getLanguage(LANGUAGE language, BufferedReader cmdReader) {
        try {
            System.out.println(getMessage("input_language", language));
            switch (cmdReader.readLine().toLowerCase()) {
                case "ru":
                    return LANGUAGE.RU;
                case "en":
                    return LANGUAGE.EN;
                default:
                    System.out.println(getWarning("default_language", language));
                    return LANGUAGE.RU;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
