package server.services.builders;

import server.exception.ApplicationException;
import server.exception.ValidationException;
import server.services.BuilderType;
import server.validation.Validation;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;

import static client.ui.ConsoleColors.error;
import static server.validation.Validation.validate;
import static util.Message.getError;
import static util.Message.getMessage;

/**
 * The type Name builder.
 */
public class NameBuilder {
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
            throw new ApplicationException(error("Ошибка BufferedReader"));
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
