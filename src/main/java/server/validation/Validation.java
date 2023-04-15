package server.validation;

import client.ui.ConsoleColors;
import server.exception.FileException;
import server.exception.ValidationException;
import server.model.Coordinates;
import server.model.Mood;
import server.model.dto.HumanBeingRequestDTO;
import util.LANGUAGE;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.util.function.Function;

import static client.ui.ConsoleColors.error;
import static client.ui.ConsoleColors.warning;
import static util.Message.getError;
import static util.Message.getWarning;
import static util.Parser.tildaResolver;

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
    public static void validateFileExist(File file) {
        if (!Files.exists(file.toPath())) {
            throw new FileException(getError("db_file_not_found", LANGUAGE.RU));
        }
    }


    /**
     * Validate file read.
     *
     * @param file the file
     */
    public static void validateFileRead(File file) {
        if (!Files.isReadable(file.toPath())) {
            throw new FileException(getError("db_file_not_readable", LANGUAGE.RU));
        }
    }

    /**
     * Validate file write.
     *
     * @param file the file
     */
    public static void validateFileWrite(File file) {
        if (!Files.isWritable(file.toPath())) {
            throw new FileException(getError("file_not_writable", LANGUAGE.RU));
        }
    }

    /**
     * Проверяет путь к файлу на корректность.
     *
     * @param fileName
     */
    public static boolean validateFileName(String fileName) {
        try {
            (new File(fileName.replace("~", ""))).toPath();
            return true;
        } catch (InvalidPathException e) {
            return false;
        }
    }

    public static void validateFile(String fileName) {
        if (!validateFileName(fileName)) {
            throw new FileException(getError("invalid_file_name", LANGUAGE.RU));
        }
        File file = new File(tildaResolver(fileName));
        if (Files.isDirectory(file.toPath())) {
            throw new FileException(getError("file_is_directory", LANGUAGE.RU));
        }
        validateFileExist(file);
        try {
            validateFileRead(file);
        } catch (FileException e) {
            System.out.println(warning("Файл недоступен к чтению! База данных не будет использована."));
        }
        try {
            validateFileWrite(file);
        } catch (FileException e) {
            System.out.println(getWarning("db_file_not_writable", LANGUAGE.RU));
        }
    }


    /**
     * Validate user name boolean.
     *
     * @param userName the user name
     * @return the boolean
     */
    public static boolean validateUserName(String userName) {
        return (userName != null && !userName.trim().equals(""));
    }

    /**
     * Validate.
     *
     * @param <T>          the type parameter
     * @param object       the object
     * @param validator    the validator
     * @param errorMessage the error message
     */
    public static <T> void validate(T object, Function<T, Boolean> validator, String errorMessage) {
        if (!validator.apply(object)) {
            throw new ValidationException(ConsoleColors.RED_BRIGHT + errorMessage + ConsoleColors.RESET);
        }
    }
}