package server.services.builders;

import server.model.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class CarBuilder {
    public static Car carBuilder(BufferedReader reader) {
        Car car = new Car();
        String name = "";
        boolean cool = false;
        try  {
            System.out.println("Введите название машины. Если ее нет, то введите null/пустую строку");
            name = reader.readLine();
            if (Objects.equals(name, "") || Objects.equals(name, "null")){
                return null;
            } else {
                car.setName(name);
                System.out.println("Введите крутая ли она? (true/false/t/f):");
                String answer = reader.readLine().toLowerCase();
                if (answer.equals("t") || answer.equals("true") || answer.equals("y")) {
                    cool = true;
                }
            }
        } catch (IOException e) {
            System.out.println("CarBuilder.carBuilder() -> Reading from keyboard error");
        }
        return new Car(name, cool);
    }
}
