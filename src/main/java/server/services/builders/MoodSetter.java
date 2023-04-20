package server.services.builders;

import server.exception.ValidationException;
import server.model.Mood;
import server.services.BuilderType;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;

import static util.Message.getError;
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
    public static Mood getMood(BufferedReader reader, LANGUAGE language) {
        int number = 0;
        try {
            number = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            throw new ValidationException(getError("number_error", language), e);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        switch (number) {
            case 1:
                return Mood.SORROW;
            case 2:
                return Mood.GLOOM;
            case 3:
                return Mood.APATHY;
            case 4:
                return Mood.CALM;
            default:
                return Mood.RAGE;
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
    public static Mood setMood(BufferedReader cmdreader, BufferedReader filereader, BuilderType type, LANGUAGE language) {
        if (type == BuilderType.CMD) {
            try {
                System.out.println(getMessage("input_mood", language));
                return getMood(cmdreader, language);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
                return setMood(cmdreader, filereader, BuilderType.CMD, language);
            }
        } else {
            try {
                return getMood(filereader, language);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
                return setMood(cmdreader, filereader, BuilderType.CMD, language);
            }
        }

    }
}
