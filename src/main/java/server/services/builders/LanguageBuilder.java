package server.services.builders;

import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import static server.services.LoggerManager.setupLogger;
import static util.Message.*;

/**
 * The type Language builder.
 */
public class LanguageBuilder {
    private static final Logger logger = Logger.getLogger(LanguageBuilder.class.getName());

    static {
        setupLogger(logger);
    }

    /**
     * Gets language.
     *
     * @param language  the language
     * @param cmdReader the cmd reader
     * @return the language
     */
    public static LANGUAGE getLanguage(LANGUAGE language, BufferedReader cmdReader) {
        try {
            logger.info(getLog("language_start"));
            System.out.println(getMessage("input_language", language));
            switch (cmdReader.readLine().toLowerCase()) {
                case "ru":
                    logger.info(getLog("language_finish"));
                    return LANGUAGE.RU;
                case "en":
                    logger.info(getLog("language_finish"));
                    return LANGUAGE.EN;
                default:
                    logger.info(getLog("language_finish"));
                    System.out.println(getWarning("default_language", language));
                    logger.warning(getWarning("default_language", LANGUAGE.EN));
                    return LANGUAGE.RU;
            }
        } catch (IOException e) {
            logger.severe(e.getMessage());
            return LANGUAGE.RU;
        }
    }
}
