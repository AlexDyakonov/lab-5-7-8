package server.validation;

import server.exception.ValidationException;
import server.model.Mood;
import server.model.WeaponType;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

import static client.ui.ConsoleColors.error;

public class Parser {
    public static boolean stringToBoolean(String line){
        boolean response = false;
        if (line.equals("true") || line.equals("t") || line.equals("y")){
            response = true;
        }
        return response;
    }
    public static ZonedDateTime stringToDateTime(String line){
        ZonedDateTime response = ZonedDateTime.parse("1991-01-01T00:00:00.713617100+03:00[Europe/Moscow]");
        try {
            response = ZonedDateTime.parse(line);
        } catch (DateTimeParseException e) {
            System.out.println(error("Не удалось сопоставить время. Будет использовано дефолтное значение."));
        }
        return response;
    }
    public static Mood stringToMood(String line){
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
            default -> {
                System.out.println((error("Значения Mood могут быть SORROW, GLOOM, APATHY, CALM, RAGE. Будет установлено дефолтное значение(RAGE).")));
                return Mood.RAGE;
            }
        }
    }

    public static WeaponType stringToWeaponType(String line){
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
            default -> {
                System.out.println(error("Значения WeaponType могут быть AXE, SHOTGUN, BAT, null. Будет установлено дефолтное значение(SHOTGUN)."));
                return WeaponType.SHOTGUN;
            }
        }
    }

}
