package client.utility;

import server.exception.FileException;
import server.exception.ValidationException;
import server.model.Coordinates;
import server.model.Mood;
import server.model.WeaponType;

import java.time.LocalDateTime;

import static client.ui.ConsoleColors.*;

/**
 * The type Converter.
 */
public class Converter {
    /**
     * To str string.
     *
     * @param line the line
     * @return the string
     */
    public static String toStr(String line){
        return line.replace("%COMMA%", ",");
    }

    /**
     * To long long.
     *
     * @param line the line
     * @return the long
     */
    public static Long toLong(String line){
        try {
            return Long.parseLong(line);
        } catch (NumberFormatException e) {
            throw new FileException(RED + line + RED_BRIGHT + " не соответствует требованию. Id в файле должно быть типа long. Запись будет проигнорирована" + RESET);
        }
    }

    /**
     * To float float.
     *
     * @param line the line
     * @return the float
     */
    public static Float toFloat(String line){
        try {
            return Float.parseFloat(line);
        } catch (NumberFormatException e) {
            throw new FileException(RED + line + RED_BRIGHT + " не соответствует требованию. Impactspeed принимает числовое значение. Запись будет проигнорирована."+ RESET);
        }
    }

    /**
     * To coordinates coordinates.
     *
     * @param line the line
     * @return the coordinates
     */
    public static Coordinates toCoordinates(String line){ // (1;1.0)
        String[] coordArr = line.replaceAll("[()]", "").split(";");
        Coordinates coordinates = new Coordinates();
        if (coordArr.length != 2){
            throw new FileException(RED_BRIGHT + "Количество координат больше 2. Запись будет проигнорирована"+ RESET);
        }
        try {
            Integer x = Integer.parseInt(coordArr[0].trim());
            Double y = Double.parseDouble(coordArr[1].trim());
            coordinates.setY(y);
            coordinates.setX(x);
            return coordinates;
        } catch (NumberFormatException e) {
            throw new FileException(RED_BRIGHT + "Координаты x,y неверного формата. Должны быть числа. Запись будет проигнорирована"+ RESET);
        }
    }

    /**
     * To local date time local date time.
     *
     * @param line the line
     * @return the local date time
     */
    public static LocalDateTime toLocalDateTime(String line){
        try {
            return LocalDateTime.parse(line);
        } catch (Exception e) {
            throw new FileException(RED_BRIGHT +"Неверно введено время создания файла. Запись будет проигнорирована"+ RESET);
        }
    }

    /**
     * To boolean boolean.
     *
     * @param line the line
     * @return the boolean
     */
    public static Boolean toBoolean(String line){
        switch (line.trim().toLowerCase()){
            case "true", "t" -> {
                return true;
            }
            case "false", "f" -> {
                return false;
            }
            default -> throw new ValidationException(RED + line + RED_BRIGHT + " не соответствует требованию. Значения не соответствуют необходимым true/false"+ RESET);
        }

    }

    /**
     * To wt weapon type.
     *
     * @param line the line
     * @return the weapon type
     */
    public static WeaponType toWT(String line){
        switch (line.trim().toLowerCase()){
            case "axe" -> {
                return WeaponType.AXE;
            }
            case "shotgun" -> {
                return WeaponType.SHOTGUN;
            }
            case "bat" -> {
                return WeaponType.BAT;
            }
            case "(null)", "null", "" -> {
                return null;
            }
            default -> throw new ValidationException(RED + line + RED_BRIGHT + " не соответствует требованию. Значения WeaponType могут быть AXE, SHOTGUN, BAT, null. Запись будет проигнорирована."+ RESET);
        }
    }

    /**
     * To mood mood.
     *
     * @param line the line
     * @return the mood
     */
    public static Mood toMood(String line){
        switch (line.trim().toLowerCase()){
            case "sorrow" -> {
                return Mood.SORROW;
            }
            case "gloom" -> {
                return Mood.GLOOM;
            }
            case "apathy" -> {
                return Mood.APATHY;
            }
            case "calm"-> {
                return Mood.CALM;
            }
            case "rage" -> {
                return Mood.RAGE;
            }
            default -> throw new ValidationException(RED + line + RED_BRIGHT + " не соответствует требованию.  Значения Mood могут быть SORROW, GLOOM, APATHY, CALM, RAGE. Запись будет проигнорирована."+ RESET);
        }
    }
}
