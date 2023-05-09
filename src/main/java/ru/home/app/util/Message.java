package ru.home.app.util;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static ru.home.app.ui.ConsoleColors.*;
import static ru.home.app.util.FileManager.updateFile;

/**
 * The type Message.
 */
public class Message {
    private static final String content;
    private static final JSONObject jsonObject;


    static {
        try {
            updateFile("https://raw.githubusercontent.com/AlexDyakonov/lab-5-num-125595/devgui/Messages.json", "Messages.json");
            Path json = Path.of("Messages.json");
            content = new String(Files.readAllBytes(json));
            jsonObject = new JSONObject(content);
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
        return whiteStr(jsonObject.getJSONObject(language.toString()).getJSONObject("message").getString(messageName));
    }

    /**
     * Gets error from json file. Using in case when need to output some fatal error, which stops any executing in red color.
     *
     * @param messageName the message name
     * @param language    the language
     * @return the error
     */
    public static String getError(String messageName, LANGUAGE language) {
        return error(jsonObject.getJSONObject(language.toString()).getJSONObject("error").getString(messageName));
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
        return warning(jsonObject.getJSONObject(language.toString()).getJSONObject("warning").getString(messageName));
    }

    /**
     * Gets success message from json file. Using in case when need to output a message of successful executing in green color.
     *
     * @param messageName the message name
     * @param language    the language
     * @return the success message
     */
    public static String getSuccessMessage(String messageName, LANGUAGE language) {
        return success(jsonObject.getJSONObject(language.toString()).getJSONObject("success").getString(messageName));
    }

    /**
     * Gets command description from json file in white color.
     *
     * @param messageName the message name
     * @param language    the language
     * @return the command description
     */
    public static String getCommandDescription(String messageName, LANGUAGE language) {
        return whiteStr(jsonObject.getJSONObject(language.toString()).getJSONObject("command_description").getString(messageName));
    }

    /**
     * Gets logs from json file with no color.
     *
     * @param messageName the message name
     * @return the command description
     */
    public static String getLog(String messageName) {
        return jsonObject.getJSONObject("log_info").getString(messageName);
    }
}
