package server.services.builders;

import server.exception.ValidationException;
import server.model.Coordinates;
import server.services.BuilderType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static client.ui.ConsoleColors.*;

public class CoordinatesBuilder {
    public static Coordinates getCoordinates(BufferedReader reader){
        Integer x = null;
        Double y = null;
        try {
            System.out.println(whiteStr("Введите координату X: "));
            x = Integer.parseInt(reader.readLine());
            System.out.println(whiteStr("Введите координату Y (больше -897): "));
            y = Double.parseDouble(reader.readLine());
            if (y < -897){
                throw new ValidationException(unsuccess("Y должен быть больше -897"));
            }
        } catch (NumberFormatException | IOException | ValidationException e) {
            System.out.println(error("CoordinatesBuilder.buildCoordinates() -> Reading from keyboard error"));
            throw new ValidationException("Координаты X, Y должны быть численными", e);
        }
        return new Coordinates(x, y);
    }
    public static Coordinates getCoordinatesFromFile(BufferedReader reader){
        Integer x = null;
        Double y = null;
        try {
            x = Integer.parseInt(reader.readLine());
            y = Double.parseDouble(reader.readLine());
            if (y < -897){
                throw new ValidationException(unsuccess("Y должен быть больше -897"));
            }
        } catch (NumberFormatException | IOException | ValidationException e) {
            System.out.println(error("CoordinatesBuilder.buildCoordinates() -> Reading from file error"));
            throw new ValidationException("Координаты X, Y должны быть численными", e);
        }
        return new Coordinates(x, y);
    }
    public static Coordinates buildCoordinates(BufferedReader cmdreader, BufferedReader filereader, BuilderType type) {
        if (type == BuilderType.CMD){
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
