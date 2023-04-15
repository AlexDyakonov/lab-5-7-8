package util;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static client.ui.ConsoleColors.*;

/**
 * The type Message.
 */
public class Message {
    private static String content;

    static {
        try {
            content = new String(Files.readAllBytes(Paths.get("src/main/resources/Messages.json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Gets message from json file. Using in case when need to output a normal message in white color.
     *
     * @param messageName the message name
     * @param language    the language
     * @return the message
     */
    public static String getMessage(String messageName, LANGUAGE language) {
        try {
            JSONObject jsonObject = new JSONObject(content);
            return whiteStr(jsonObject.getJSONObject(language.toString()).getJSONObject("message").getString(messageName));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets error from json file. Using in case when need to output some fatal error, which stops any executing in red color.
     *
     * @param messageName the message name
     * @param language    the language
     * @return the error
     */
    public static String getError(String messageName, LANGUAGE language) {
        try {
            JSONObject jsonObject = new JSONObject(content);
            return error(jsonObject.getJSONObject(language.toString()).getJSONObject("error").getString(messageName));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets warning from json file. Using in case when need to output a warning message in yellow color and nothing was interrupted.
     * Use when set default values etc.
     *
     * @param messageName the message name
     * @param language    the language
     * @return the warning
     */
    public static String getWarning(String messageName, LANGUAGE language) {
        try {
            JSONObject jsonObject = new JSONObject(content);
            return warning(jsonObject.getJSONObject(language.toString()).getJSONObject("warning").getString(messageName));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets success message from json file. Using in case when need to output a message of successful executing in green color.
     *
     * @param messageName the message name
     * @param language    the language
     * @return the success message
     */
    public static String getSuccessMessage(String messageName, LANGUAGE language) {
        try {
            JSONObject jsonObject = new JSONObject(content);
            return success(jsonObject.getJSONObject(language.toString()).getJSONObject("success").getString(messageName));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets command description from json file in white color.
     *
     * @param messageName the message name
     * @param language    the language
     * @return the command description
     */
    public static String getCommandDescription(String messageName, LANGUAGE language) {
        try {
            JSONObject jsonObject = new JSONObject(content);
            return whiteStr(jsonObject.getJSONObject(language.toString()).getJSONObject("command_description").getString(messageName));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
