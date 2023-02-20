package Validation;

import exception.ValidationException;
import model.Coordinates;
import model.Mood;
import ui.ConsoleColors;

import java.util.function.Function;

public class ValidationImpl {
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
