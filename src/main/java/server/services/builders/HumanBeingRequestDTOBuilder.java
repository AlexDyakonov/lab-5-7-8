package server.services.builders;

import server.model.dto.HumanBeingRequestDTO;
import server.services.BuilderType;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.util.logging.Logger;

import static server.services.LoggerManager.setupLogger;
import static server.services.builders.BooleanBuilder.boolBuilder;
import static server.services.builders.ImpactSpeedBuilder.impactSpeedBuilder;
import static server.services.builders.NameBuilder.nameBuilder;
import static util.Message.getLog;


/**
 * The type Human being request dto builder.
 */
public class HumanBeingRequestDTOBuilder {
    private static final Logger logger = Logger.getLogger(HumanBeingRequestDTOBuilder.class.getName());

    static {
        setupLogger(logger);
    }

    /**
     * Build human being request dto.
     *
     * @param cmdreader   the cmdreader
     * @param filereader  the filereader
     * @param builderType the builder type
     * @param language    the language
     * @return the human being request dto
     */
    public static HumanBeingRequestDTO build(BufferedReader cmdreader, BufferedReader filereader, BuilderType builderType, LANGUAGE language) {
        HumanBeingRequestDTO dto = new HumanBeingRequestDTO();

        dto.setName(nameBuilder(cmdreader, filereader, "input_name", builderType, language));
        dto.setCoordinates(CoordinatesBuilder.buildCoordinates(cmdreader, filereader, builderType, language));

        dto.setRealHero(boolBuilder(cmdreader, filereader, "input_realhero", builderType, language));
        dto.setHasToothpick(boolBuilder(cmdreader, filereader, "input_has_toothpick", builderType, language));

        dto.setImpactSpeed(impactSpeedBuilder(cmdreader, filereader, builderType, language));
        dto.setSoundtrackName(nameBuilder(cmdreader, filereader, "input_soundtrack", builderType, language));

        dto.setWeaponType(WeaponTypeSetter.setWeaponType(cmdreader, filereader, builderType, language));
        dto.setMood(MoodSetter.setMood(cmdreader, filereader, builderType, language));

        dto.setCar(CarBuilder.carBuilder(cmdreader, filereader, builderType, language));

        return dto;
    }

}