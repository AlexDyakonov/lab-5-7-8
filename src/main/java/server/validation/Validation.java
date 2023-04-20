package server.validation;

import client.ui.ConsoleColors;
import server.exception.FileException;
import server.exception.ValidationException;
import server.model.Coordinates;
import server.model.dto.HumanBeingRequestDTO;
import util.LANGUAGE;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.function.Function;

import static util.Message.getError;
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
        return dto.getMood() != null;
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
    public static void validateFileExist(File file, LANGUAGE language) {
        if (!Files.exists(file.toPath())) {
            throw new FileException(getError("db_file_not_found", language));
        }
    }


    /**
     * Validate file read.
     *
     * @param file the file
     */
    public static void validateFileRead(File file, LANGUAGE language) {
        if (!Files.isReadable(file.toPath())) {
            throw new FileException(getError("db_file_not_readable", language));
        }
    }

    /**
     * Validate file write.
     *
     * @param file the file
     */
    public static void validateFileWrite(File file, LANGUAGE language) {
        if (!Files.isWritable(file.toPath())) {
            throw new FileException(getError("file_not_writable", language));
        }
    }

    /**
     * Проверяет путь к файлу на корректность.
     *
     * @param fileName
     */
    public static void validateFileName(String fileName, LANGUAGE language) {
        try {
            (new File(fileName.replace("~", ""))).toPath();
        } catch (InvalidPathException e) {
            throw new FileException(getError("invalid_file_name", language));
        }
    }

    public static void validateFileDirectory(String fileName, LANGUAGE language) {
        if (Files.isDirectory(Paths.get(fileName))) {
            throw new FileException(getError("file_is_directory", language));
        }
    }


    public static void validateFile(String fileName, LANGUAGE language) {
        fileName = tildaResolver(fileName);
        validateFileName(fileName, language);
        File file = new File(fileName);
        validateFileDirectory(fileName, language);
        validateFileExist(file, language);
        validateFileRead(file, language);
        validateFileWrite(file, language);
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