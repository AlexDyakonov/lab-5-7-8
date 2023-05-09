package ru.home.app.server.services.builders;

import ru.home.app.server.exception.FileException;
import ru.home.app.server.exception.ValidationException;
import ru.home.app.server.services.BuilderType;
import ru.home.app.util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import static ru.home.app.server.services.LoggerManager.setupLogger;
import static ru.home.app.util.Message.getError;
import static ru.home.app.util.Message.getMessage;
import static ru.home.app.util.Parser.stringToBoolean;

/**
 * The type Boolean builder.
 */
public class BooleanBuilder {
    private static final Logger logger = Logger.getLogger(BooleanBuilder.class.getName());

    static {
        setupLogger(logger);
    }

    /**
     * Gets bool.
     *
     * @param reader   the reader
     * @param language the language
     * @return the bool
     */
    public static boolean getBool(BufferedReader reader, LANGUAGE language) {
        boolean response = false;
        String request;
        try {
            request = reader.readLine();
            if (request == null) {
                throw new ValidationException(getError("bool_null", language));
            }
            request = request.toLowerCase();
            response = stringToBoolean(request);
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
        return response;
    }

    /**
     * Bool builder boolean.
     *
     * @param cmdreader  the cmdreader
     * @param filereader the filereader
     * @param messageId  the message id
     * @param type       the type
     * @param language   the language
     * @return the boolean
     */
    public static boolean boolBuilder(BufferedReader cmdreader, BufferedReader filereader, String messageId, BuilderType type, LANGUAGE language) {
        if (type == BuilderType.CMD) {
            try {
                System.out.println(getMessage(messageId, language));
                return getBool(cmdreader, language);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
                return boolBuilder(cmdreader, filereader, messageId, BuilderType.CMD, language);
            }
        } else {
            try {
                return getBool(filereader, language);
            } catch (ValidationException | FileException e) {
                System.out.println(e.getMessage());
                return boolBuilder(cmdreader, filereader, messageId, BuilderType.CMD, language);
            }
        }
    }
}
