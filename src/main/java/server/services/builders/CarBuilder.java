package server.services.builders;

import server.model.Car;
import server.services.BuilderType;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

import static client.ui.ConsoleColors.error;
import static server.services.LoggerManager.setupLogger;
import static util.Message.getMessage;

/**
 * The type Car builder.
 */
public class CarBuilder {
    private static final Logger logger = Logger.getLogger(CarBuilder.class.getName());

    static {
        setupLogger(logger);
    }

    /**
     * Gets car.
     *
     * @param reader   the reader
     * @param language the language
     * @return the car
     */
    public static Car getCar(BufferedReader reader, LANGUAGE language) {
        Car car = new Car();
        String name = "";
        boolean cool = false;
        try {
            System.out.println(getMessage("input_car_name", language));
            name = reader.readLine();
            if (Objects.equals(name, "") || Objects.equals(name, "null") || name == null) {
                return null;
            } else {
                car.setName(name);
                System.out.println(getMessage("input_car_cool", language));
                String answer = reader.readLine().toLowerCase();
                if (answer.equals("t") || answer.equals("true") || answer.equals("y")) {
                    cool = true;
                }
            }
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
        return new Car(name, cool);
    }

    /**
     * Gets car from file.
     *
     * @param reader the reader
     * @return the car from file
     */
    public static Car getCarFromFile(BufferedReader reader) {
        Car car = new Car();
        String name = "";
        boolean cool = false;
        try {
            name = reader.readLine();
            if (Objects.equals(name, "") || Objects.equals(name, "null")) {
                return null;
            } else {
                car.setName(name);
                String answer = reader.readLine().toLowerCase();
                if (answer.equals("t") || answer.equals("true") || answer.equals("y")) {
                    cool = true;
                }
            }
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
        return new Car(name, cool);
    }

    /**
     * Car builder car.
     *
     * @param cmdreader  the cmdreader
     * @param filereader the filereader
     * @param type       the type
     * @param language   the language
     * @return the car
     */
    public static Car carBuilder(BufferedReader cmdreader, BufferedReader filereader, BuilderType type, LANGUAGE language) {
        if (type == BuilderType.CMD) {
            try {
                return getCar(cmdreader, language);
            } catch (Exception e) {
                return carBuilder(cmdreader, filereader, BuilderType.CMD, language);
            }
        } else {
            try {
                return getCarFromFile(filereader);
            } catch (Exception e) {
                return carBuilder(cmdreader, filereader, BuilderType.CMD, language);
            }
        }

    }
}
