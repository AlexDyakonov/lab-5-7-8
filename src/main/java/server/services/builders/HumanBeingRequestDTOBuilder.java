package server.services.builders;

import server.exception.ApplicationException;
import server.exception.ValidationException;
import server.model.dto.HumanBeingRequestDTO;
import server.services.BuilderType;
import server.validation.Validation;

import java.io.BufferedReader;
import java.io.IOException;

import static client.ui.ConsoleColors.*;
import static server.services.builders.BooleanBuilder.boolBuilder;
import static server.services.builders.ImpactSpeedBuilder.impactSpeedBuilder;
import static server.services.builders.NameBuilder.nameBuilder;
import static server.validation.Parser.stringToBoolean;
import static server.validation.Validation.validate;

public class HumanBeingRequestDTOBuilder {
    public static HumanBeingRequestDTO build(BufferedReader reader, BuilderType builderType) {
        HumanBeingRequestDTO dto = new HumanBeingRequestDTO();

        if (builderType == BuilderType.CMD){
            dto.setName(nameBuilder(reader, "Введите имя: "));
            dto.setCoordinates(CoordinatesBuilder.buildCoordinates(reader, builderType));

            dto.setRealHero(boolBuilder(reader, "Это реальный герой? (true/t/y, default: false) "));
            dto.setHasToothpick(boolBuilder(reader, "У него есть зубочистка? (true/t/y, default: false) "));

            dto.setImpactSpeed(impactSpeedBuilder(reader));
            dto.setSoundtrackName(nameBuilder(reader, "Введите название саундтрека: "));

            dto.setWeaponType(WeaponTypeSetter.setWeaponType(reader));
            dto.setMood(MoodSetter.setMood(reader));
            dto.setCar(CarBuilder.carBuilder(reader));

            return dto;
        } else {
            dto.setCoordinates(CoordinatesBuilder.buildCoordinates(reader, builderType));

            dto.setRealHero(boolBuilder(reader));
            dto.setHasToothpick(boolBuilder(reader));

            dto.setImpactSpeed(impactSpeedBuilder(reader));
            dto.setSoundtrackName(nameBuilder(reader));

            dto.setWeaponType(WeaponTypeSetter.setWeaponType(reader));
            dto.setMood(MoodSetter.setMood(reader));
            dto.setCar(CarBuilder.carBuilder(reader));
            return dto;

        }
    }

}