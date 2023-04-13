package server.services.builders;

import server.exception.ApplicationException;
import server.exception.ValidationException;
import server.services.BuilderType;
import server.validation.Validation;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;

import static client.ui.ConsoleColors.*;
import static server.validation.Validation.validate;
import static util.Message.getMessage;

/**
 * The type Name builder.
 */
public class NameBuilder {
    /**
     * Get name string.
     *
     * @param reader the reader
     * @return the string
     */
    public static String getName(BufferedReader reader){
        try {
            String name = reader.readLine();
            validate(name, Validation::validateUserName, error("Имя не должно быть пустым и должно быть больше 0 символа"));
            return name;
        } catch (IOException e){
            throw new ApplicationException(error("Ошибка BufferedReader"));
        }
    }

    /**
     * Name builder string.
     *
     * @param cmdreader the reader 1
     * @param filereader the reader 2
     * @param messageId the message
     * @param type    the type
     * @return the string
     */
    public static String nameBuilder(BufferedReader cmdreader, BufferedReader filereader, String messageId, BuilderType type) {
        if (type == BuilderType.CMD){
            try {
                System.out.println(getMessage(messageId, LANGUAGE.RU));
                return getName(cmdreader);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
                return nameBuilder(cmdreader, filereader, messageId, BuilderType.CMD);
            }
        } else {
            try {
                return getName(filereader);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
                return nameBuilder(cmdreader, filereader, messageId, BuilderType.CMD);
            }
        }
    }
}
