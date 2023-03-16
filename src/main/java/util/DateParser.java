package util;

import java.time.ZonedDateTime;

public class DateParser {

    public static ZonedDateTime zoneDateTimeFromString(String string) {
        return ZonedDateTime.parse(string);
    }
}


