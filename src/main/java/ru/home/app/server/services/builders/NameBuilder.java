package ru.home.app.server.services.builders;

import ru.home.app.server.exception.ValidationException;
import ru.home.app.server.validation.Validation;
import ru.home.app.util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import static ru.home.app.server.services.LoggerManager.setupLogger;
import static ru.home.app.server.validation.Validation.validate;
import static ru.home.app.util.Message.getError;
import static ru.home.app.util.Message.getMessage;

/**
 * The type Name builder.
 */
public class NameBuilder {
    private static final Logger logger = Logger.getLogger(NameBuilder.class.getName());

    static {
        setupLogger(logger);
    }

    /**
     * Gets name.
     *
     * @param reader the reader
     * @return the name
     */
    public static String getName(BufferedReader reader) {
        try {
            String name = reader.readLine();
            validate(name, Validation::validateUserName, getError("invalid_name", LANGUAGE.EN));
            return name;
        } catch (IOException e) {
            logger.severe(e.getMessage());
            return "ErrorName";
        }
    }

    /**
     * Name builder string.
     *
     * @param cmdreader  the cmdreader
     * @param filereader the filereader
     * @param messageId  the message id
     * @param type       the type
     * @param language   the language
     * @return the string
     */
    public static String nameBuilder(BufferedReader cmdreader, BufferedReader filereader, String messageId, BuilderType type, LANGUAGE language) {
        if (type == BuilderType.CMD) {
            try {
                System.out.println(getMessage(messageId, language));
                return getName(cmdreader);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
                return nameBuilder(cmdreader, filereader, messageId, BuilderType.CMD, language);
            }
        } else {
            try {
                return getName(filereader);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
                return nameBuilder(cmdreader, filereader, messageId, BuilderType.CMD, language);
            }
        }
    }
}
