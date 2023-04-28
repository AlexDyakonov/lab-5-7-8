package server.authentication;


import server.controller.HumanController;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import static server.services.builders.UserBuilder.getPasswordLogin;
import static server.services.builders.UserBuilder.getUserName;

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

    public void menu() {
//        System.out.println(getMessage("menu", language));
        System.out.println("Добро пожаловать! Войдите в аккаунт для того чтобы начать пользоваться " +
                "приложением или пройдите регистрацию, чтобы получить доступ к нему.");
        System.out.println("1. Вход \n2. Регистрация \n3. Войти как гость");
        try {
            switch (reader.readLine()) {
                case "1" -> login();
                case "2" -> registerUser();
                case "3" -> guest();
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
                System.out.println("Username already exists");
                registerUser();
            } else {
                controller.userRegister(username, password);
                userManager.setUserRole(ROLES.USER).setUserName(username);
                System.out.println("Registration successful");
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
                System.out.println("Login successful");
            } else {
                System.out.println("Invalid username or password");
            }
            return userManager;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public UserManager guest() {
        return userManager;
    }
}
