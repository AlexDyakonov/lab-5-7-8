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

public class Validation {

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
    public static boolean validateFile(File file){
        if (!Files.exists(file.toPath())){
            throw new FileException("Файл не найден.");
        }
        if (!Files.isReadable(file.toPath())){
            throw new FileException("Файл недоступен для чтения");
        }
        if (Files.isWritable(file.toPath())){
            throw new FileException("Файл недоступен для записи");
        }
        return true;
    }
    public static boolean validateUserName(String userName){
        return (userName != null && !userName.trim().equals(""));
    }

    public static boolean validateCarName(String carNamr){
        return (carNamr != null && !carNamr.trim().equals(""));
    }

    public static boolean validateCoordinates(Coordinates coordinates){
        return coordinates.getX() != null && coordinates.getY() != null;
    }

    public static boolean validateBoolean(Boolean bool){
        return bool != null;
    }

    public static boolean validateImpactSpeed(Float impactSpeed){
        return (impactSpeed != null);
    }

    public static boolean validateSoundtrackName(String soundtrackName){
        return (soundtrackName != null && !soundtrackName.trim().equals(""));
    }

    public static boolean validateMood(Mood mood){
        return (mood != null);
    }

    public static boolean validateId(String id){
        return (id != null && !id.contains(","));
    }

    public static <T> void validate(T object, Function<T, Boolean> validator, String errorMessage){
        if (!validator.apply(object)){
            throw new ValidationException(ConsoleColors.RED_BRIGHT + errorMessage + ConsoleColors.RESET);
        }
    }
}