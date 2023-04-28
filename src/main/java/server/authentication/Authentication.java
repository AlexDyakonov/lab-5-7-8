package server.authentication;


import server.controller.HumanController;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import static server.services.builders.UserBuilder.getPasswordLogin;
import static server.services.builders.UserBuilder.getUserName;
import static util.Message.*;

public class Authentication {
    private static final Logger logger = Logger.getLogger(Authentication.class.getName());
    private final UserManager userManager = new UserManager();
    private final HumanController controller;
    private BufferedReader reader;
    private LANGUAGE language;

    public Authentication(HumanController controller, BufferedReader reader, LANGUAGE language) {
        this.reader = reader;
        this.controller = controller;
        this.language = language;
    }

    public void start() {
        System.out.println(getMessage("authentication", language));
        try {
            switch (reader.readLine()) {
                case "1" -> login();
                case "2" -> registerUser();
                case "3" -> guest();
                case "exit" -> System.exit(0);
                default -> start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public UserManager registerUser() {
        try {
            String username = getUserName(reader, language);
            String password = getPasswordLogin(reader, language);
            if (controller.getUserNameList().contains(username)) {
                System.out.println(getWarning("user_exist", language));
                registerUser();
            } else {
                controller.userRegister(username, password);
                userManager.setUserRole(ROLES.USER).setUserName(username);
                System.out.println(getSuccessMessage("user_registered", language));
            }
            return userManager;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public UserManager login() {
        try {
            String username = getUserName(reader, language);
            String password = getPasswordLogin(reader, language);
            if (controller.checkUserPassword(username, password)) {
                userManager.setUserName(username).setUserRole(controller.getUserRole(username));
                System.out.println(getSuccessMessage("done", language));
            } else {
                System.out.println(getWarning("invalid_login", language));
                login();
            }
            return userManager;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public UserManager guest() {
        userManager.setUserRole(ROLES.GUEST).setUserName("guest");
        return userManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }
}
