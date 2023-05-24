package ru.home.app.server.validation;

import javafx.scene.control.TableView;
import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.exception.AuthenticationException;
import ru.home.app.server.exception.FileException;
import ru.home.app.server.exception.ValidationException;
import ru.home.app.server.model.Coordinates;
import ru.home.app.server.model.dto.HumanBeingRequestDTO;
import ru.home.app.server.model.dto.HumanBeingResponseDTOwithUsers;
import ru.home.app.util.LANGUAGE;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.function.Function;

import static ru.home.app.util.Message.getError;
import static ru.home.app.util.Parser.tildaResolver;

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

    public static boolean validateCreator(TableView table, CurrentUserManager userManager, String message){
        if (!((HumanBeingResponseDTOwithUsers) table.getSelectionModel().getSelectedItem()).getUsername().equals(userManager.getUserName())){
            throw new AuthenticationException(message);
        }
        return true;
    }

    /**
     * Validate file exist.
     *
     * @param file     the file
     * @param language the language
     */
    public static void validateFileExist(File file, LANGUAGE language) {
        if (!Files.exists(file.toPath())) {
            throw new FileException(getError("db_file_not_found", language));
        }
    }


    /**
     * Validate file read.
     *
     * @param file     the file
     * @param language the language
     */
    public static void validateFileRead(File file, LANGUAGE language) {
        if (!Files.isReadable(file.toPath())) {
            throw new FileException(getError("db_file_not_readable", language));
        }
    }

    /**
     * Validate file write.
     *
     * @param file     the file
     * @param language the language
     */
    public static void validateFileWrite(File file, LANGUAGE language) {
        if (!Files.isWritable(file.toPath())) {
            throw new FileException(getError("file_not_writable", language));
        }
    }

    /**
     * Validate file name.
     *
     * @param fileName the file name
     * @param language the language
     */
    public static void validateFileName(String fileName, LANGUAGE language) {
        try {
            (new File(fileName.replace("~", ""))).toPath();
        } catch (InvalidPathException e) {
            throw new FileException(getError("invalid_file_name", language));
        }
    }

    /**
     * Validate file directory.
     *
     * @param fileName the file name
     * @param language the language
     */
    public static void validateFileDirectory(String fileName, LANGUAGE language) {
        if (Files.isDirectory(Paths.get(fileName))) {
            throw new FileException(getError("file_is_directory", language));
        }
    }


    /**
     * Validate file.
     *
     * @param fileName the file name
     * @param language the language
     */
    public static void validateFile(String fileName, LANGUAGE language) {
        fileName = tildaResolver(fileName);
        validateFileName(fileName, language);
        File file = new File(fileName);
        validateFileDirectory(fileName, language);
        validateFileExist(file, language);
        validateFileRead(file, language);
        validateFileWrite(file, language);
    }

// TODO сделать все методы для валидации.

    /**
     * Validate user name boolean.
     *
     * @param userName the user name
     * @return the boolean
     */
    public static boolean validateString(String userName) {
        return (userName != null && !userName.trim().equals(""));
    }

    public static boolean validateCoordinates(Coordinates coordinates) {
        return (coordinates != null && coordinates.getX() != null && coordinates.getY() != null);
    }

    public static boolean validatePassword(String password) {
        return (password != null && !password.trim().equals(""));
    }

    public static boolean validateId(Long id) {
        return (id != null && !(id < 0));
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
            throw new ValidationException(errorMessage);
        }
    }
}