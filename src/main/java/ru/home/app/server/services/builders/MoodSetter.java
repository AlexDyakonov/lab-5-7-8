package ru.home.app.server.services.builders;

import ru.home.app.server.exception.ValidationException;
import ru.home.app.server.model.Mood;
import ru.home.app.server.services.BuilderType;
import ru.home.app.util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import static ru.home.app.server.services.LoggerManager.setupLogger;
import static ru.home.app.util.Message.getError;
import static ru.home.app.util.Message.getMessage;

/**
 * The type Mood setter.
 */
public class MoodSetter {
    private static final Logger logger = Logger.getLogger(MoodSetter.class.getName());

    static {
        setupLogger(logger);
    }

    /**
     * Gets mood.
     *
     * @param reader   the reader
     * @param language the language
     * @return the mood
     */
    public static Mood getMood(BufferedReader reader, LANGUAGE language) {
        int number = 0;
        try {
            number = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            throw new ValidationException(getError("number_error", language), e);
        } catch (IOException e) {
            logger.severe(e.getMessage());
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
     * @param language   the language
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
