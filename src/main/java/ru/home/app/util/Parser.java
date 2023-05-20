package ru.home.app.util;

import ru.home.app.server.authentication.ROLES;
import ru.home.app.server.exception.ValidationException;
import ru.home.app.server.model.Mood;
import ru.home.app.server.model.WeaponType;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

import static ru.home.app.ui.ConsoleColors.error;
import static ru.home.app.util.Message.getError;

/**
 * The type Parser.
 */
public class Parser {
    public static String[] moodToStringArray(LANGUAGE language) {
        return new String[]{"Sorrow", "Gloom", "Apathy", "Calm", "Rage"};
    }

    public static String[] weaponToStringArray(LANGUAGE language) {
        return new String[]{"Axe", "Shotgun", "Bat"};
    }

    /**
     * String to id long.
     *
     * @param string the string
     * @return the long
     */
    public static Long stringToId(String string) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException e) {
            throw new ValidationException(getError("id_not_correct", LANGUAGE.EN));
        }
    }

    /**
     * Tilda resolver string.
     *
     * @param file the file
     * @return the string
     */
    public static String tildaResolver(String file) {
        if (file.startsWith("~")) {
            file = file.replaceFirst("^~", System.getProperty("user.home"));
        }
        return file;
    }

    /**
     * String to boolean boolean.
     *
     * @param line the line
     * @return the boolean
     */
    public static boolean stringToBoolean(String line) {
        boolean response = line.equals("true") || line.equals("t") || line.equals("y");
        return response;
    }

    /**
     * String to date time zoned date time.
     *
     * @param line the line
     * @return the zoned date time
     */
    public static ZonedDateTime stringToDateTime(String line) {
        ZonedDateTime response = ZonedDateTime.parse("1991-01-01T00:00:00.713617100+03:00[Europe/Moscow]");
        try {
            response = ZonedDateTime.parse(line);
        } catch (DateTimeParseException e) {
            System.out.println(error("Не удалось сопоставить время. Будет использовано дефолтное значение."));
        }
        return response;
    }

    /**
     * String to mood mood.
     *
     * @param line the line
     * @return the mood
     */
    public static Mood stringToMood(String line) {
        switch (line.trim().toLowerCase()) {
            case "sorrow" -> {
                return Mood.SORROW;
            }
            case "gloom" -> {
                return Mood.GLOOM;
            }
            case "apathy" -> {
                return Mood.APATHY;
            }
            case "calm" -> {
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

    /**
     * String to weapon type weapon type.
     *
     * @param line the line
     * @return the weapon type
     */
    public static WeaponType stringToWeaponType(String line) {
        switch (line.trim().toLowerCase()) {
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

    /**
     * String to role roles.
     *
     * @param line the line
     * @return the roles
     */
    public static ROLES stringToRole(String line) {
        switch (line.trim().toLowerCase()) {
            case "admin", "-1" -> {
                return ROLES.ADMIN;
            }
            case "guest", "0" -> {
                return ROLES.GUEST;
            }
            case "user", "1" -> {
                return ROLES.USER;
            }
            default -> {
                return ROLES.GUEST;
            }
        }
    }

    /**
     * Convert time stamp to zoned zoned date time.
     *
     * @param timestamp the timestamp
     * @return the zoned date time
     */
    public static ZonedDateTime convertTimeStampToZoned(Timestamp timestamp) {
        Instant instant = timestamp.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId);
    }

    /**
     * Convert zoned date time to time stamp timestamp.
     *
     * @param zonedDateTime the zoned date time
     * @return the timestamp
     */
    public static Timestamp convertZonedDateTimeToTimeStamp(ZonedDateTime zonedDateTime) {
        return Timestamp.valueOf(zonedDateTime.toLocalDateTime());
    }

    public static String userAvatarIdToPathString(int id) {
        return "resources/ru/home/app/assets/avatars/ava" + id + ".jpg";
    }
}
