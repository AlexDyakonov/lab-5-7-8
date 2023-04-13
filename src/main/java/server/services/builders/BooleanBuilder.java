package server.services.builders;

import server.exception.FileException;
import server.exception.ValidationException;
import server.services.BuilderType;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;

import static client.ui.ConsoleColors.error;
import static util.Message.getMessage;
import static util.Parser.stringToBoolean;

/**
 * The type Boolean builder.
 */
public class BooleanBuilder {
    /**
     * Get bool boolean.
     *
     * @param reader the reader
     * @return the boolean
     */
    public static boolean getBool(BufferedReader reader) {
        boolean response = false;
        String request;
        try {
            request = reader.readLine();
            if (request == null) {
                throw new ValidationException(error("Встречен null при чтения строки для boolean поля."));
            }
            request = request.toLowerCase();
            response = stringToBoolean(request);
        } catch (IOException e) {
            System.out.println(error("HumanBeingRequestDTOBuilder.build -> Ошибка чтения"));
        }
        return response;
    }

    /**
     * Bool builder boolean.
     *
     * @param cmdreader  the cmdreader
     * @param filereader the filereader
     * @param messageId  the message
     * @param type       the type
     * @return the boolean
     */
    public static boolean boolBuilder(BufferedReader cmdreader, BufferedReader filereader, String messageId, BuilderType type) {
        if (type == BuilderType.CMD) {
            try {
                System.out.println(getMessage(messageId, LANGUAGE.RU));
                return getBool(cmdreader);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
                return boolBuilder(cmdreader, filereader, messageId, BuilderType.CMD);
            }
        } else {
            try {
                return getBool(filereader);
            } catch (ValidationException | FileException e) {
                System.out.println(e.getMessage());
                return boolBuilder(cmdreader, filereader, messageId, BuilderType.CMD);
            }
        }
    }

}
