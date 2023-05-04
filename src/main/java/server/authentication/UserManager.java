package server.authentication;

import java.util.logging.Logger;

import static server.services.LoggerManager.setupLogger;
import static util.Message.getLog;

/**
 * The type User manager. Class to hold info about current user
 */
public class UserManager {
    private static final Logger logger = Logger.getLogger(UserManager.class.getName());
    private String userName;
    private Long userId;
    private ROLES userRole;

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
    public UserManager(String userName, Long userId, ROLES role) {
        this.userName = userName;
        this.userId = userId;
        this.userRole = role;
    }

    /**
     * Instantiates a new User manager.
     */
    public UserManager() {
        logger.info(getLog("um_inited"));
    }

    /**
     * Gets user name.
     *
     * @return the user name
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
    public UserManager setUserName(String userName) {
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
    public UserManager setUserRole(ROLES userRole) {
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
}
