package server.services.builders;

import server.exception.ValidationException;
import server.model.Coordinates;
import server.services.BuilderType;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static client.ui.ConsoleColors.*;
import static util.Message.getMessage;

/**
 * The type Coordinates builder.
 */
public class CoordinatesBuilder {
    /**
     * Get coordinates coordinates.
     *
     * @param reader the reader
     * @return the coordinates
     */
    public static Coordinates getCoordinates(BufferedReader reader) {
        Integer x = null;
        Double y = null;
        try {
            System.out.println(getMessage("input_coordinate_x", LANGUAGE.RU));
            x = Integer.parseInt(reader.readLine());
            System.out.println(getMessage("input_coordinate_y", LANGUAGE.RU));
            y = Double.parseDouble(reader.readLine());
            if (y < -897) {
                throw new ValidationException(unsuccess("Y должен быть больше -897"));
            }
        } catch (NumberFormatException | IOException | ValidationException e) {
            System.out.println(error("CoordinatesBuilder.buildCoordinates() -> Reading from keyboard error"));
            throw new ValidationException("Координаты X, Y должны быть численными", e);
        }
        return new Coordinates(x, y);
    }

    /**
     * Get coordinates from file coordinates.
     *
     * @param reader the reader
     * @return the coordinates
     */
    public static Coordinates getCoordinatesFromFile(BufferedReader reader) {
        Integer x = null;
        Double y = null;
        try {
            x = Integer.parseInt(reader.readLine());
            y = Double.parseDouble(reader.readLine());
            if (y < -897) {
                throw new ValidationException(unsuccess("Y должен быть больше -897"));
            }
        } catch (NumberFormatException | IOException | ValidationException e) {
            System.out.println(error("CoordinatesBuilder.buildCoordinates() -> Reading from file error"));
            throw new ValidationException("Координаты X, Y должны быть численными", e);
        }
        return new Coordinates(x, y);
    }

    /**
     * Build coordinates coordinates.
     *
     * @param cmdreader  the cmdreader
     * @param filereader the filereader
     * @param type       the type
     * @return the coordinates
     */
    public static Coordinates buildCoordinates(BufferedReader cmdreader, BufferedReader filereader, BuilderType type) {
        if (type == BuilderType.CMD) {
            try {
                return getCoordinates(cmdreader);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return buildCoordinates(cmdreader, filereader, BuilderType.CMD);
            }
        } else {
            try {
                return getCoordinatesFromFile(filereader);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return buildCoordinates(cmdreader, filereader, BuilderType.CMD);
            }
        }

    }

}
