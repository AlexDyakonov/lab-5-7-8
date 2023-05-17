package ru.home.app.server.services.builders;

import ru.home.app.server.exception.ApplicationException;
import ru.home.app.server.exception.FileException;
import ru.home.app.server.exception.ValidationException;
import ru.home.app.util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import static ru.home.app.server.services.LoggerManager.setupLogger;
import static ru.home.app.util.Message.getError;
import static ru.home.app.util.Message.getMessage;

/**
 * The type Impact speed builder.
 */
public class ImpactSpeedBuilder {
    private static final Logger logger = Logger.getLogger(ImpactSpeedBuilder.class.getName());

    static {
        setupLogger(logger);
    }

    /**
     * Gets impact speed.
     *
     * @param reader   the reader
     * @param language the language
     * @return the impact speed
     */
    public static float getImpactSpeed(BufferedReader reader, LANGUAGE language) {
        try {
            String num = reader.readLine();
            if (num == null) {
                throw new ValidationException(getError("impact_speed_null", language));
            }
            return Float.parseFloat(num);
        } catch (NumberFormatException e) {
            throw new ValidationException(getError("number_error", language), e);
        } catch (IOException e) {
            logger.severe(e.getMessage());
            return -1;
        }
    }

    /**
     * Impact speed builder float.
     *
     * @param cmdReader  the cmd reader
     * @param fileReader the file reader
     * @param type       the type
     * @param language   the language
     * @return the float
     */
    public static float impactSpeedBuilder(BufferedReader cmdReader, BufferedReader fileReader, BuilderType type, LANGUAGE language) {
        if (type == BuilderType.CMD) {
            try {
                System.out.println(getMessage("input_impact_speed", language));
                return getImpactSpeed(cmdReader, language);
            } catch (ValidationException | ApplicationException e) {
                System.out.println(e.getMessage());
                return impactSpeedBuilder(cmdReader, fileReader, BuilderType.CMD, language);
            }
        } else {
            try {
                return getImpactSpeed(fileReader, language);
            } catch (ValidationException | ApplicationException | FileException e) {
                System.out.println(e.getMessage());
                return impactSpeedBuilder(cmdReader, fileReader, BuilderType.CMD, language);
            }
        }
    }
}
