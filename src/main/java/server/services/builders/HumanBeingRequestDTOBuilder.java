package server.services.builders;

import server.model.dto.HumanBeingRequestDTO;
import server.services.BuilderType;

import java.io.BufferedReader;

import static server.services.builders.BooleanBuilder.boolBuilder;
import static server.services.builders.ImpactSpeedBuilder.impactSpeedBuilder;
import static server.services.builders.NameBuilder.nameBuilder;


public class HumanBeingRequestDTOBuilder {
    public static HumanBeingRequestDTO build(BufferedReader reader, BuilderType builderType) {
        HumanBeingRequestDTO dto = new HumanBeingRequestDTO();

            dto.setName(nameBuilder(reader, "Введите имя: ", builderType));
            dto.setCoordinates(CoordinatesBuilder.buildCoordinates(reader, builderType));

            dto.setRealHero(boolBuilder(reader, "Это реальный герой? (true/t/y, default: false) ", builderType));
            dto.setHasToothpick(boolBuilder(reader, "У него есть зубочистка? (true/t/y, default: false) ", builderType));

            dto.setImpactSpeed(impactSpeedBuilder(reader, builderType));
            dto.setSoundtrackName(nameBuilder(reader, "Введите название саундтрека: ", builderType));

            dto.setWeaponType(WeaponTypeSetter.setWeaponType(reader, builderType));
            dto.setMood(MoodSetter.setMood(reader, builderType));

            dto.setCar(CarBuilder.carBuilder(reader, builderType));

            return dto;
    }

}