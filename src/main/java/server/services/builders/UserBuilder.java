package server.services.builders;

import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import static server.services.LoggerManager.setupLogger;

public class UserBuilder {
    private static final Logger logger = Logger.getLogger(UserBuilder.class.getName());
    private LANGUAGE language;

    static {
        setupLogger(logger);
    }

    public static String getUserName(BufferedReader reader, LANGUAGE language) throws IOException {
        String login;
//        System.out.println(getMessage("get_login", language));
        System.out.println("Введите логин:");
        login = reader.readLine();
        return login;
    }

    public static String getPasswordLogin(BufferedReader reader, LANGUAGE language) throws IOException {
        String login;
//        System.out.println(getMessage("get_pass", language));
        System.out.println("Введите пароль:");
        login = reader.readLine();
        return login;
    }

    public static String confirmUserPassword(BufferedReader reader, LANGUAGE language) throws IOException {
        String login;
//        System.out.println(getMessage("get_login", language));
        System.out.println("Повторите пароль:");
        login = reader.readLine();
        return login;
    }
}