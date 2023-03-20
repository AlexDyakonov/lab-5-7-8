package util;

import java.time.ZonedDateTime;

/**
 * The type Date parser.
 */
public class DateParser {

    /**
     * Zone date time from string zoned date time.
     *
     * @param string the string
     * @return the zoned date time
     */
    public static ZonedDateTime zoneDateTimeFromString(String string) {
        return ZonedDateTime.parse(string);
    }
}


