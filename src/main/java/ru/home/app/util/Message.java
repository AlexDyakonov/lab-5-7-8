package ru.home.app.util;

import org.json.JSONObject;
import ru.home.app.util.language.LANGUAGE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static ru.home.app.ui.ConsoleColors.*;

/**
 * The type Message.
 */
public class Message {
    private static final String messages;
    private static final JSONObject jsonMessages;
    private static final String messagesGUI;
    private static final JSONObject jsonMessagesGUI;
    private static final String messagesLog;
    private static final JSONObject jsonMessagesLog;

    static {
        try {
            Path json = Path.of("src/main/resources/localizations/Messages.json");
            messages = new String(Files.readAllBytes(json));
            jsonMessages = new JSONObject(messages);

            Path json2 = Path.of("src/main/resources/localizations/MessagesGUI.json");
            messagesGUI = new String(Files.readAllBytes(json2));
            jsonMessagesGUI = new JSONObject(messagesGUI);

            Path json3 = Path.of("src/main/resources/localizations/MessagesLog.json");
            messagesLog = new String(Files.readAllBytes(json3));
            jsonMessagesLog = new JSONObject(messagesLog);
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
        return whiteStr(jsonMessages.getJSONObject(language.toString()).getJSONObject("message").getString(messageName));
    }

    /**
     * Gets error from json file. Using in case when need to output some fatal error, which stops any executing in red color.
     *
     * @param messageName the message name
     * @param language    the language
     * @return the error
     */
    public static String getError(String messageName, LANGUAGE language) {
        return error(jsonMessages.getJSONObject(language.toString()).getJSONObject("error").getString(messageName));
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
        return warning(jsonMessages.getJSONObject(language.toString()).getJSONObject("warning").getString(messageName));
    }

    /**
     * Gets success message from json file. Using in case when need to output a message of successful executing in green color.
     *
     * @param messageName the message name
     * @param language    the language
     * @return the success message
     */
    public static String getSuccessMessage(String messageName, LANGUAGE language) {
        return success(jsonMessages.getJSONObject(language.toString()).getJSONObject("success").getString(messageName));
    }

    /**
     * Gets command description from json file in white color.
     *
     * @param messageName the message name
     * @param language    the language
     * @return the command description
     */
    public static String getCommandDescription(String messageName, LANGUAGE language) {
        return whiteStr(jsonMessages.getJSONObject(language.toString()).getJSONObject("command_description").getString(messageName));
    }

    /**
     * Gets logs from json file with no color.
     *
     * @param messageName the message name
     * @return the command description
     */
    public static String getLog(String messageName) {
        return jsonMessagesLog.getJSONObject("log_info").getString(messageName);
    }
    public static String getMessagesGUI(String messagesName, LANGUAGE language){
        return jsonMessagesGUI.getJSONObject(language.toString()).getJSONObject("?").getString(messagesName);
    }
}
