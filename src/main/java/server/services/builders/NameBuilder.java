package server.services.builders;

import server.exception.ApplicationException;
import server.exception.ValidationException;
import server.services.BuilderType;
import server.validation.Validation;

import java.io.BufferedReader;
import java.io.IOException;

import static client.ui.ConsoleColors.*;
import static server.validation.Validation.validate;

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
     * @param reader1 the reader 1
     * @param reader2 the reader 2
     * @param message the message
     * @param type    the type
     * @return the string
     */
    public static String nameBuilder(BufferedReader reader1, BufferedReader reader2, String message, BuilderType type) {
        if (type == BuilderType.CMD){
            try {
                System.out.println(whiteStr(message));
                return getName(reader1);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
                return nameBuilder(reader1, reader2, message, BuilderType.CMD);
            }
        } else {
            try {
                return getName(reader2);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
                return nameBuilder(reader1, reader2, message, BuilderType.CMD);
            }
        }
    }
}
