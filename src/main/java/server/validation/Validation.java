package server.validation;

import client.ui.ConsoleColors;
import server.exception.FileException;
import server.exception.ValidationException;
import server.model.Coordinates;
import server.model.Mood;
import server.model.dto.HumanBeingRequestDTO;

import java.io.File;
import java.nio.file.Files;
import java.util.function.Function;

/**
 * The type Validation.
 */
public class Validation {

    /**
     * Validate request dto boolean.
     *
     * @param dto the dto
     * @return the boolean
     */
    public static boolean validateRequestDTO(HumanBeingRequestDTO dto) {
        if (validateName(dto.getName())) return false;
        if (validateCoordinate(dto.getCoordinates())) return false;
        if (dto.getRealHero() == null) return false;
        if (dto.getHasToothpick() == null) return false;
        if (dto.getSoundtrackName() == null) return false;
        if (dto.getSoundtrackName().equals("")) return false;
        if (dto.getWeaponType() == null) return false;
        if (dto.getMood() == null) return false;
        return true;
    }

    private static boolean validateName(String name) {
        return !(name != null && name.length() > 0);
    }

    private static boolean validateCoordinate(Coordinates coordinates) {
        return !(coordinates.getX() != null && coordinates.getY() != null && coordinates.getY() > -897);
    }

    /**
     * Validate file exist.
     *
     * @param file the file
     */
    public static void validateFileExist(File file){
        if (!Files.exists(file.toPath())){
            throw new FileException("Файл не найден. Будет создан файл database.csv. Чтобы использовать другой запустите программу еще раз.");
        }
    }


    /**
     * Validate file read.
     *
     * @param file the file
     */
    public static void validateFileRead(File file){
        validateFileExist(file);
        if (!Files.isReadable(file.toPath())){
            throw new FileException("Файл недоступен для чтения. Будет создан файл database.csv. Чтобы использовать другой запустите программу еще раз.");
        }
    }

    /**
     * Validate file write.
     *
     * @param file the file
     */
    public static void validateFileWrite(File file){
        validateFileExist(file);
        if (!Files.isWritable(file.toPath())){
            throw new FileException("Файл недоступен для записи. Будет создан файл database.csv. Чтобы использовать другой запустите программу еще раз.");
        }
    }


    /**
     * Validate user name boolean.
     *
     * @param userName the user name
     * @return the boolean
     */
    public static boolean validateUserName(String userName){
        return (userName != null && !userName.trim().equals(""));
    }

    /**
     * Validate car name boolean.
     *
     * @param carNamr the car namr
     * @return the boolean
     */
    public static boolean validateCarName(String carNamr){
        return (carNamr != null && !carNamr.trim().equals(""));
    }

    /**
     * Validate coordinates boolean.
     *
     * @param coordinates the coordinates
     * @return the boolean
     */
    public static boolean validateCoordinates(Coordinates coordinates){
        return coordinates.getX() != null && coordinates.getY() != null;
    }

    /**
     * Validate boolean boolean.
     *
     * @param bool the bool
     * @return the boolean
     */
    public static boolean validateBoolean(Boolean bool){
        return bool != null;
    }

    /**
     * Validate impact speed boolean.
     *
     * @param impactSpeed the impact speed
     * @return the boolean
     */
    public static boolean validateImpactSpeed(Float impactSpeed){
        return (impactSpeed != null);
    }

    /**
     * Validate soundtrack name boolean.
     *
     * @param soundtrackName the soundtrack name
     * @return the boolean
     */
    public static boolean validateSoundtrackName(String soundtrackName){
        return (soundtrackName != null && !soundtrackName.trim().equals(""));
    }

    /**
     * Validate mood boolean.
     *
     * @param mood the mood
     * @return the boolean
     */
    public static boolean validateMood(Mood mood){
        return (mood != null);
    }

    /**
     * Validate id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public static boolean validateId(String id){
        return (id != null && !id.contains(","));
    }

    /**
     * Validate.
     *
     * @param <T>          the type parameter
     * @param object       the object
     * @param validator    the validator
     * @param errorMessage the error message
     */
    public static <T> void validate(T object, Function<T, Boolean> validator, String errorMessage){
        if (!validator.apply(object)){
            throw new ValidationException(ConsoleColors.RED_BRIGHT + errorMessage + ConsoleColors.RESET);
        }
    }
}