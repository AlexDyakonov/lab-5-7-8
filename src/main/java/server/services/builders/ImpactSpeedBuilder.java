package server.services.builders;

import server.exception.ApplicationException;
import server.exception.ValidationException;

import java.io.BufferedReader;
import java.io.IOException;

import static client.ui.ConsoleColors.*;

public class ImpactSpeedBuilder {
    public static float getImpactSpeed(BufferedReader reader){
        System.out.println(whiteStr("Введите ImpactSpeed (ex: 4.61): "));

        try {
            return Float.parseFloat(reader.readLine());
        } catch (NumberFormatException e) {
            throw new ValidationException(unsuccess("Значение impactSpeed должно быть числом. Введите еще раз."), e);
        } catch (IOException e2){
            throw new ApplicationException(error("HumanBeingRequestDTOBuilder.build -> Ошибка чтения из клавиатуры"));
        }
    }
    public static float impactSpeedBuilder(BufferedReader reader){
        try {
            return getImpactSpeed(reader);
        } catch (ValidationException | ApplicationException e) {
            System.out.println(e.getMessage());
            return impactSpeedBuilder(reader);
        }
    }
}
