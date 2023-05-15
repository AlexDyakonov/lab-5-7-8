package ru.home.app.server.model;

import ru.home.app.server.authentication.ROLES;

public class User {
    private Long userId;
    private String userName;
    private ROLES userRole;
    private int userAvatarId;

    public User(String userName, Long userId, ROLES userRole, int userAvatarId) {
        this.userName = userName;
        this.userId = userId;
        this.userRole = userRole;
        this.userAvatarId = userAvatarId;
    }

    public User(Long userId, String userName, ROLES userRole) {
        this.userId = userId;
        this.userName = userName;
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

    public int getUserAvatarId() {
        return userAvatarId;
    }

    public void setUserAvatarId(int userAvatarId) {
        this.userAvatarId = userAvatarId;
    }

    public User(Long userId, String userName, ROLES userRole, int userAvatarId) {
        this.userId = userId;
        this.userName = userName;
        this.userRole = userRole;
        this.userAvatarId = userAvatarId;
    }
}
