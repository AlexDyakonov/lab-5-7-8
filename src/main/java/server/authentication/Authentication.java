package server.authentication;


import server.controller.HumanController;
import server.exception.ValidationException;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import static server.services.LoggerManager.setupLogger;
import static server.services.builders.UserBuilder.getPasswordLogin;
import static server.services.builders.UserBuilder.getUserName;
import static util.Message.*;

/**
 * The type Authentication.  Class that begins authentication for users
 */
public class Authentication {
    private static final Logger logger = Logger.getLogger(Authentication.class.getName());
    private final UserManager userManager = new UserManager();
    private final HumanController controller;
    private final BufferedReader reader;
    private final LANGUAGE language;

    static {
        setupLogger(logger);
    }

    /**
     * Instantiates a new Authentication.
     *
     * @param controller the controller
     * @param reader     the reader
     * @param language   the language
     */
    public Authentication(HumanController controller, BufferedReader reader, LANGUAGE language) {
        logger.info(getLog("auth_init_start"));
        this.reader = reader;
        this.controller = controller;
        this.language = language;
        controller.setUserName(userManager.getUserName());
        logger.info(getLog("auth_init_finish"));
    }

    /**
     * configs userManager for actual user
     *
     * @param username
     */
    private void configUserManager(String username) {
        ROLES role = controller.getUserRole(username);
        Long id = controller.getUserId(username);
        userManager.setUserName(username)
                .setUserRole(role)
                .setUserId(id);
        logger.info(getLog("user_manager_cofigured").
                replace("%id%", String.valueOf(id)).
                replace("%name%", username).
                replace("%role%", role.toString()));
    }

    /**
     * Start. Print message and give choice for user
     */
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
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Register user user manager. Method to register user
     *
     * @return the user manager
     */
    public UserManager registerUser() {
        try {
            String username = getUserName(reader, language);
            String password = getPasswordLogin(reader, language);
            if (controller.getUserNameList().contains(username)) {
                System.out.println(getWarning("user_exist", language));
                logger.warning(getWarning("user_exist", LANGUAGE.EN));
                registerUser();
            } else {
                controller.userRegister(username, password);
                configUserManager(username);
                controller.setUserManager(userManager);
                System.out.println(getSuccessMessage("user_registered", language));
                logger.info(getLog("user_regisrered"));
            }
            return userManager;
        } catch (ValidationException e) {
            logger.warning(e.getMessage());
            System.out.println(e.getMessage());
            return login();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Login user manager. Method to login user
     *
     * @return the user manager
     */
    public UserManager login() {
        try {
            String username = getUserName(reader, language);
            String password = getPasswordLogin(reader, language);
            if (controller.checkUserPassword(username, password)) {
                configUserManager(username);
                controller.setUserManager(userManager);
                System.out.println(getSuccessMessage("done", language));
                logger.info(getLog("user_logined"));
            } else {
                System.out.println(getWarning("invalid_login", language));
                logger.info(getWarning("invalid_login", LANGUAGE.EN));
                login();
            }
            return userManager;
        } catch (ValidationException e) {
            logger.warning(e.getMessage());
            System.out.println(e.getMessage());
            return login();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Guest user manager. Method to use guest account
     *
     * @return the user manager
     */
    public UserManager guest() {
        userManager.setUserRole(ROLES.GUEST).setUserName("guest");
        logger.info(getLog("guest_logined"));
        return userManager;
    }

    /**
     * Gets user manager.
     *
     * @return the user manager
     */
    public UserManager getUserManager() {
        return userManager;
    }
}
