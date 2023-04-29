package server.model;

import server.authentication.ROLES;

public class User {
    private Long userId;
    private String userName;
    private ROLES userRole;

    public User(String userName, Long userId, ROLES userRole) {
        this.userName = userName;
        this.userId = userId;
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ROLES getUserRole() {
        return userRole;
    }

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
