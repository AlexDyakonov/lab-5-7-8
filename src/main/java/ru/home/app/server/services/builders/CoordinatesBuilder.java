package ru.home.app.server.services.builders;

import ru.home.app.server.exception.ValidationException;
import ru.home.app.server.model.Coordinates;
import ru.home.app.server.services.BuilderType;
import ru.home.app.util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import static ru.home.app.server.services.LoggerManager.setupLogger;
import static ru.home.app.util.Message.*;

/**
 * The type Coordinates builder.
 */
public class CoordinatesBuilder {
    private static final Logger logger = Logger.getLogger(CoordinatesBuilder.class.getName());

    static {
        setupLogger(logger);
    }

    /**
     * Gets coordinates.
     *
     * @param reader   the reader
     * @param language the language
     * @return the coordinates
     */
    public static Coordinates getCoordinates(BufferedReader reader, LANGUAGE language) {
        int x;
        double y;
        try {
            System.out.println(getMessage("input_coordinate_x", language));
            x = Integer.parseInt(reader.readLine());
            System.out.println(getMessage("input_coordinate_y", language));
            y = Double.parseDouble(reader.readLine());
            if (y < -897) {
                throw new ValidationException(getWarning("y_more_than", language));
            }
        } catch (NumberFormatException | ValidationException e) {
            throw new ValidationException(getError("number_error", language), e);
        } catch (IOException e) {
            logger.severe(e.getMessage());
            x = 0;
            y = 0;
        }
        return new Coordinates(x, y);
    }

    /**
     * Gets coordinates from file.
     *
     * @param reader   the reader
     * @param language the language
     * @return the coordinates from file
     */
    public static Coordinates getCoordinatesFromFile(BufferedReader reader, LANGUAGE language) {
        int x;
        double y;
        try {
            x = Integer.parseInt(reader.readLine());
            y = Double.parseDouble(reader.readLine());
            if (y < -897) {
                throw new ValidationException(getWarning("y_more_than", language));
            }
        } catch (NumberFormatException | ValidationException e) {
            throw new ValidationException(getError("number_error", language), e);
        } catch (IOException e) {
            logger.severe(e.getMessage());
            x = 0;
            y = 0;
        }
        return new Coordinates(x, y);
    }

    /**
     * Build coordinates coordinates.
     *
     * @param cmdreader  the cmdreader
     * @param filereader the filereader
     * @param type       the type
     * @param language   the language
     * @return the coordinates
     */
    public static Coordinates buildCoordinates(BufferedReader cmdreader, BufferedReader filereader, BuilderType type, LANGUAGE language) {
        if (type == BuilderType.CMD) {
            try {
                return getCoordinates(cmdreader, language);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return buildCoordinates(cmdreader, filereader, BuilderType.CMD, language);
            }
        } else {
            try {
                return getCoordinatesFromFile(filereader, language);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return buildCoordinates(cmdreader, filereader, BuilderType.CMD, language);
            }
        }

    }

}
