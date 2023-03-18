package server.services.builders;

import server.model.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import static client.ui.ConsoleColors.error;
import static client.ui.ConsoleColors.whiteStr;

public class CarBuilder {
    public static Car getCar(BufferedReader reader) {
        Car car = new Car();
        String name = "";
        boolean cool = false;
        try  {
            System.out.println(whiteStr("Введите название машины. Если ее нет, то введите null/пустую строку"));
            name = reader.readLine();
            if (Objects.equals(name, "") || Objects.equals(name, "null")){
                return null;
            } else {
                car.setName(name);
                System.out.println(whiteStr("Введите крутая ли она? (true/false/t/f):"));
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

        public static Car carBuilder(BufferedReader reader) {
        try {
            return getCar(reader);
        } catch (Exception e){
            return carBuilder(reader);
        }
    }
}
