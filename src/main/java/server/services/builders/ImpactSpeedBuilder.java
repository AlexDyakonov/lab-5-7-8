package server.services.builders;

import server.exception.ApplicationException;
import server.exception.ValidationException;
import server.services.BuilderType;

import java.io.BufferedReader;
import java.io.IOException;

import static client.ui.ConsoleColors.*;

public class ImpactSpeedBuilder {
    public static float getImpactSpeed(BufferedReader reader){
        try {
            return Float.parseFloat(reader.readLine());
        } catch (NumberFormatException e) {
            throw new ValidationException(unsuccess("Значение impactSpeed должно быть числом. Введите еще раз."), e);
        } catch (IOException e2){
            throw new ApplicationException(error("HumanBeingRequestDTOBuilder.build -> Ошибка чтения из клавиатуры"));
        }
    }
    public static float impactSpeedBuilder(BufferedReader cmdReader, BufferedReader fileReader, BuilderType type){
        if (type == BuilderType.CMD){
            try {
                System.out.println(whiteStr("Введите ImpactSpeed (ex: 4.61): "));
                return getImpactSpeed(cmdReader);
            } catch (ValidationException | ApplicationException e) {
                System.out.println(e.getMessage());
                return impactSpeedBuilder(cmdReader, fileReader, BuilderType.CMD);
            }
        } else {
            try {
                return getImpactSpeed(fileReader);
            } catch (ValidationException | ApplicationException e) {
                System.out.println(e.getMessage());
                return impactSpeedBuilder(cmdReader, fileReader, BuilderType.CMD);
            }
        }
    }
}
