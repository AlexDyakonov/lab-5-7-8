package server.services.builders;

import server.exception.ValidationException;
import server.model.Mood;
import server.services.BuilderType;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static client.ui.ConsoleColors.*;
import static util.Message.getMessage;

/**
 * The type Mood setter.
 */
public class MoodSetter {
    /**
     * Gets mood.
     *
     * @param reader the reader
     * @return the mood
     */
    public static Mood getMood(BufferedReader reader) {
        int number = 0;
        try {
            number = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e){
            throw new ValidationException(unsuccess("Введите числовые значения."), e);
        } catch (IOException e) {
            System.out.println(error("Ошибка BufferedReader."));
        }
        switch (number) {
            case 1: return Mood.SORROW;
            case 2: return Mood.GLOOM;
            case 3: return Mood.APATHY;
            case 4: return Mood.CALM;
            default: return Mood.RAGE;
        }
    }

    /**
     * Sets mood.
     *
     * @param cmdreader  the cmdreader
     * @param filereader the filereader
     * @param type       the type
     * @return the mood
     */
    public static Mood setMood(BufferedReader cmdreader, BufferedReader filereader, BuilderType type) {
        if (type == BuilderType.CMD){
            try {
                System.out.println(getMessage("input_weapon", LANGUAGE.RU));
                return getMood(cmdreader);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
                return setMood(cmdreader, filereader, BuilderType.CMD);
            }
        } else {
            try {
                return getMood(filereader);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
                return setMood(cmdreader, filereader, BuilderType.CMD);
            }
        }

    }
}
