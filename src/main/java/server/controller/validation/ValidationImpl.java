package server.controller.Validation;

import server.exception.ValidationException;
import server.model.Coordinates;
import server.model.Mood;
import client.ui.ConsoleColors;

import java.util.function.Function;

/**
 * The type server.controller.Validation.
 */
public class ValidationImpl {
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
