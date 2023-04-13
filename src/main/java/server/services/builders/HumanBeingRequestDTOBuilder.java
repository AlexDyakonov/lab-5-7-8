package server.services.builders;

import server.model.dto.HumanBeingRequestDTO;
import server.services.BuilderType;

import java.io.BufferedReader;

import static server.services.builders.BooleanBuilder.boolBuilder;
import static server.services.builders.ImpactSpeedBuilder.impactSpeedBuilder;
import static server.services.builders.NameBuilder.nameBuilder;


/**
 * The type Human being request dto builder.
 */
public class HumanBeingRequestDTOBuilder {
    /**
     * Build human being request dto.
     *
     * @param cmdreader   the cmdreader
     * @param filereader  the filereader
     * @param builderType the builder type
     * @return the human being request dto
     */
    public static HumanBeingRequestDTO build(BufferedReader cmdreader, BufferedReader filereader, BuilderType builderType) {
        HumanBeingRequestDTO dto = new HumanBeingRequestDTO();

        dto.setName(nameBuilder(cmdreader, filereader, "input_name", builderType));
        dto.setCoordinates(CoordinatesBuilder.buildCoordinates(cmdreader, filereader, builderType));

        dto.setRealHero(boolBuilder(cmdreader, filereader, "input_realhero", builderType));
        dto.setHasToothpick(boolBuilder(cmdreader, filereader, "input_has_toothpick", builderType));

        dto.setImpactSpeed(impactSpeedBuilder(cmdreader, filereader, builderType));
        dto.setSoundtrackName(nameBuilder(cmdreader, filereader, "input_soundtrack", builderType));

        dto.setWeaponType(WeaponTypeSetter.setWeaponType(cmdreader, filereader, builderType));
        dto.setMood(MoodSetter.setMood(cmdreader, filereader, builderType));

        dto.setCar(CarBuilder.carBuilder(cmdreader, filereader, builderType));

        return dto;
    }

}