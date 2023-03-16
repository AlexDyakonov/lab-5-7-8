package server.services.builders;

import server.model.Coordinates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CoordinatesBuilder {
    public static Coordinates buildCoordinates(BufferedReader reader) throws IOException {
        Integer x = null;
        Double y = null;
            System.out.println("Enter 'x' coordinate: ");
            x = Integer.parseInt(reader.readLine());
            System.out.println("Enter 'y' coordinate: ");
            y = Double.parseDouble(reader.readLine());
            System.out.println("CoordinatesBuilder.buildCoordinates() -> Reading from keyboard error");
        return new Coordinates(x, y);
    }
}
