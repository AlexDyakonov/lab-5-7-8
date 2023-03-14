package server.services.builders;

import server.model.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CarBuilder {
    public static Car carBuilder() {
        String name = "";
        boolean cool = false;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter car`s name: ");
            name = reader.readLine();
            System.out.println("Car is cool? Y/N: ");
            String answer = reader.readLine();
            if (answer.equals("Y")) {
                cool = true;
            }
        } catch (IOException e) {
            System.out.println("CarBuilder.carBuilder() -> Reading from keyboard error");
        }
        return new Car(name, cool);
    }
}