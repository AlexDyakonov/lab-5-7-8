package server.services.builders;

import server.exception.ApplicationException;
import server.exception.ValidationException;
import server.model.dto.HumanBeingRequestDTO;
import server.validation.Validation;

import java.io.BufferedReader;
import java.io.IOException;

import static client.ui.ConsoleColors.*;
import static server.validation.Parser.stringToBoolean;
import static server.validation.Validation.validate;

public class HumanBeingRequestDTOBuilder {
    public static HumanBeingRequestDTO build(BufferedReader reader) {
        HumanBeingRequestDTO dto = new HumanBeingRequestDTO();

        dto.setName(nameBuilder(reader, "Введите имя: "));
        dto.setCoordinates(CoordinatesBuilder.buildCoordinates(reader));

        dto.setRealHero(boolBuilder(reader, "Это реальный герой? (true/t/y, default: false) "));
        dto.setHasToothpick(boolBuilder(reader, "У него есть зубочистка? (true/t/y, default: false) "));

        dto.setImpactSpeed(impactSpeedBuilder(reader));
        dto.setSoundtrackName(nameBuilder(reader, "Введите название саундтрека: "));

        dto.setWeaponType(WeaponTypeSetter.setWeaponType(reader));
        dto.setMood(MoodSetter.setMood(reader));
        dto.setCar(CarBuilder.carBuilder(reader));

        return dto;
    }

    private static String getName(BufferedReader reader, String message){
        try {
            System.out.println(whiteStr(message));
            String name = reader.readLine();
            validate(name, Validation::validateUserName, error("Имя не должно быть пустым и должно быть больше 0 символа"));
            return name;
        } catch (IOException e){
            throw new ApplicationException(error("Ошибка BufferedReader"));
        }
    }

    private static String nameBuilder(BufferedReader reader, String message) {
        try {
            return getName(reader, message);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return nameBuilder(reader, message);
        }
    }
    private static boolean getBool(BufferedReader reader){
        boolean response = false;
        String request = "";
        try {
            request = reader.readLine().toLowerCase();
            response = stringToBoolean(request);
        } catch (IOException e) {
            System.out.println(error("HumanBeingRequestDTOBuilder.build -> Ошибка чтения из клавиатуры"));
        }
        return response;
    }
    private static boolean boolBuilder(BufferedReader reader, String message){
        try {
            System.out.println(whiteStr(message));
            return getBool(reader);
        } catch (ValidationException e){
            System.out.println(e.getMessage());
            return boolBuilder(reader, message);
        }
    }
    private static float getImpactSpeed(BufferedReader reader){
        System.out.println(whiteStr("Введите ImpactSpeed (ex: 4.61): "));

        try {
            return Float.parseFloat(reader.readLine());
        } catch (NumberFormatException e) {
            throw new ValidationException(unsuccess("Значение impactSpeed должно быть числом. Введите еще раз."), e);
        } catch (IOException e2){
            throw new ApplicationException(error("HumanBeingRequestDTOBuilder.build -> Ошибка чтения из клавиатуры"));
        }
    }
    private static float impactSpeedBuilder(BufferedReader reader){
        try {
            return getImpactSpeed(reader);
        } catch (ValidationException | ApplicationException e) {
            System.out.println(e.getMessage());
            return impactSpeedBuilder(reader);
        }
    }
}