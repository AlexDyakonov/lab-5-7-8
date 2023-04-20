package server.services.builders;

import server.exception.ValidationException;
import server.model.WeaponType;
import server.services.BuilderType;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import static server.services.LoggerManager.setupLogger;
import static util.Message.getError;
import static util.Message.getMessage;

/**
 * The type Weapon type setter.
 */
public class WeaponTypeSetter {
    private static final Logger logger = Logger.getLogger(WeaponTypeSetter.class.getName());

    static {
        setupLogger(logger);
    }

    /**
     * Gets weapon type.
     *
     * @param reader   the reader
     * @param language the language
     * @return the weapon type
     */
    public static WeaponType getWeaponType(BufferedReader reader, LANGUAGE language) {
        int number = 0;
        try {
            number = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            throw new ValidationException(getError("number_error", language), e);
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
        switch (number) {
            case 1:
                return WeaponType.AXE;
            case 2:
                return WeaponType.SHOTGUN;
            case 3:
                return WeaponType.BAT;
            case 0:
                return null;
            default:
                return WeaponType.SHOTGUN;
        }
    }

    /**
     * Sets weapon type.
     *
     * @param cmdreader  the cmdreader
     * @param filereader the filereader
     * @param type       the type
     * @param language   the language
     * @return the weapon type
     */
    public static WeaponType setWeaponType(BufferedReader cmdreader, BufferedReader filereader, BuilderType type, LANGUAGE language) {
        if (type == BuilderType.CMD) {
            try {
                System.out.println(getMessage("input_weapon", language));
                return getWeaponType(cmdreader, language);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
                return setWeaponType(cmdreader, filereader, BuilderType.CMD, language);
            }
        } else {
            try {
                return getWeaponType(filereader, language);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
                return setWeaponType(cmdreader, filereader, BuilderType.CMD, language);
            }
        }
    }
}