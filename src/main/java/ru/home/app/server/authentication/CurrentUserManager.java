package ru.home.app.server.authentication;

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
    private int userAvatarId;

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

    public CurrentUserManager(String userName, Long userId, ROLES userRole, int userAvatarId) {
        this.userName = userName;
        this.userId = userId;
        this.userRole = userRole;
        this.userAvatarId = userAvatarId;
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
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getUserAvatarId() {
        return userAvatarId;
    }

    public void setUserAvatarId(int userAvatarId) {
        this.userAvatarId = userAvatarId;
    }
}
