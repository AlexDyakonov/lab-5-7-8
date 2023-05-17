package ru.home.app.server.authentication;

import ru.home.app.server.controller.GuiHumanController;
import ru.home.app.server.model.User;

import java.util.List;
import java.util.logging.Logger;

import static ru.home.app.server.services.LoggerManager.setupLogger;
import static ru.home.app.util.Message.getLog;

/**
 * The type User manager. Class to hold info about current user
 */
public class CurrentUserManager {
    private static final Logger logger = Logger.getLogger(CurrentUserManager.class.getName());
    private String userName;
    private Long userId;
    private ROLES userRole;
    private String userAvatar;

    static {
        setupLogger(logger);
    }

    /**
     * Instantiates a new User manager.
     *
     * @param userName the user name
     * @param userId   the user id
     * @param role     the role
     */
    public CurrentUserManager(String userName, Long userId, ROLES role) {
        this.userName = userName;
        this.userId = userId;
        this.userRole = role;
    }

    public CurrentUserManager(String userName, Long userId, ROLES userRole, String userAvatar) {
        this.userName = userName;
        this.userId = userId;
        this.userRole = userRole;
        this.userAvatar = userAvatar;
    }

    /**
     * Instantiates a new User manager.
     */
    public CurrentUserManager() {
        logger.info(getLog("um_inited"));
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     * @return the user name
     */
    public CurrentUserManager setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * Gets user role.
     *
     * @return the user role
     */
    public ROLES getUserRole() {
        return userRole;
    }

    /**
     * Sets user role.
     *
     * @param userRole the user role
     * @return the user role
     */
    public CurrentUserManager setUserRole(ROLES userRole) {
        this.userRole = userRole;
        return this;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public CurrentUserManager setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public CurrentUserManager configUserManager(String username, GuiHumanController controller) {
        List<User> allUsers = controller.getAllUsers();
        this.setUserName(username)
                .setUserRole(allUsers.stream().filter(p -> p.getUserName().equals(username)).toList().get(0).getUserRole())
                .setUserId(allUsers.stream().filter(p -> p.getUserName().equals(username)).toList().get(0).getUserId())
                .setUserAvatar(allUsers.stream().filter(p -> p.getUserName().equals(username)).toList().get(0).getUserAvatar());
        return this;
    }
    public CurrentUserManager clear(){
        return new CurrentUserManager();
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
