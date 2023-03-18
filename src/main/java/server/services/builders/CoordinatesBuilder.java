package server.services.builders;

import server.exception.ValidationException;
import server.model.Coordinates;

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
    public static Coordinates buildCoordinates(BufferedReader reader) {
           try {
               return getCoordinates(reader);
           } catch (Exception e) {
               System.out.println(e.getMessage());
               return buildCoordinates(reader);
           }
    }
}
