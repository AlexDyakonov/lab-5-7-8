package util;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static client.ui.ConsoleColors.error;
import static client.ui.ConsoleColors.whiteStr;

public class Message {
    private static String content;

    static {
        try {
            content = new String(Files.readAllBytes(Paths.get("src/main/resources/Messages.json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String getMessage(String messageName, LANGUAGE language) {
        try {
            JSONObject jsonObject = new JSONObject(content);
            return whiteStr(jsonObject.getJSONObject(language.toString()).getJSONObject("message").getString(messageName));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getError(String messageName, LANGUAGE language) {
        try {
            JSONObject jsonObject = new JSONObject(content);
            return error(jsonObject.getJSONObject(language.toString()).getJSONObject("error").getString(messageName));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
