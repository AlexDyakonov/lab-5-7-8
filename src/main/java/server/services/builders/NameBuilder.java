package server.services.builders;

import server.exception.ApplicationException;
import server.exception.ValidationException;
import server.validation.Validation;

import java.io.BufferedReader;
import java.io.IOException;

import static client.ui.ConsoleColors.error;
import static client.ui.ConsoleColors.whiteStr;
import static server.validation.Validation.validate;

public class NameBuilder {
    public static String getName(BufferedReader reader){
        try {
            String name = reader.readLine();
            validate(name, Validation::validateUserName, error("Имя не должно быть пустым и должно быть больше 0 символа"));
            return name;
        } catch (IOException e){
            throw new ApplicationException(error("Ошибка BufferedReader"));
        }
    }

    public static String nameBuilder(BufferedReader reader, String message) {
        try {
            System.out.println(whiteStr(message));
            return getName(reader);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return nameBuilder(reader, message);
        }
    }
    public static String nameBuilder(BufferedReader reader) {
        try {
            return getName(reader);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return nameBuilder(reader);
        }
    }
}
