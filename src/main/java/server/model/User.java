package server.model;

import server.authentication.ROLES;

/**
 * The type User. User class to hold info about any user
 */
public class User {
    private Long userId;
    private String userName;
    private ROLES userRole;

    /**
     * Instantiates a new User.
     *
     * @param userName the user name
     * @param userId   the user id
     * @param userRole the user role
     */
    public User(String userName, Long userId, ROLES userRole) {
        this.userName = userName;
        this.userId = userId;
        this.userRole = userRole;
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
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     */
    public void setUserRole(ROLES userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
