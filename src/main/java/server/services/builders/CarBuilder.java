package server.services.builders;

import org.apache.commons.collections.map.LazyMap;
import server.model.Car;
import server.services.BuilderType;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import static client.ui.ConsoleColors.error;
import static client.ui.ConsoleColors.whiteStr;
import static util.Message.getMessage;

/**
 * The type Car builder.
 */
public class CarBuilder {
    /**
     * Gets car.
     *
     * @param reader the reader
     * @return the car
     */
    public static Car getCar(BufferedReader reader) {
        Car car = new Car();
        String name = "";
        boolean cool = false;
        try  {
            System.out.println(getMessage("input_car_name", LANGUAGE.RU));
            name = reader.readLine();
            if (Objects.equals(name, "") || Objects.equals(name, "null") || name == null){
                return null;
            } else {
                car.setName(name);
                System.out.println(getMessage("input_car_cool", LANGUAGE.RU));
                String answer = reader.readLine().toLowerCase();
                if (answer.equals("t") || answer.equals("true") || answer.equals("y")) {
                    cool = true;
                }
            }
        } catch (IOException e) {
            System.out.println(error("CarBuilder.carBuilder() -> Reading from keyboard error"));
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
        try  {
            name = reader.readLine();
            if (Objects.equals(name, "") || Objects.equals(name, "null")){
                return null;
            } else {
                car.setName(name);
                String answer = reader.readLine().toLowerCase();
                if (answer.equals("t") || answer.equals("true") || answer.equals("y")) {
                    cool = true;
                }
            }
        } catch (IOException e) {
            System.out.println(error("CarBuilder.carBuilder() -> Reading from keyboard error"));
        }
        return new Car(name, cool);
    }

    /**
     * Car builder car.
     *
     * @param cmdreader  the cmdreader
     * @param filereader the filereader
     * @param type       the type
     * @return the car
     */
    public static Car carBuilder(BufferedReader cmdreader, BufferedReader filereader, BuilderType type) {
        if (type == BuilderType.CMD){
            try {
                return getCar(cmdreader);
            } catch (Exception e){
                return carBuilder(cmdreader, filereader, BuilderType.CMD);
            }
        } else {
            try {
                return getCarFromFile(filereader);
            } catch (Exception e){
                return carBuilder(cmdreader, filereader, BuilderType.CMD);
            }
        }

    }
}
