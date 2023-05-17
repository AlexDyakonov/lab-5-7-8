package ru.home.app.server.model;

import ru.home.app.server.authentication.ROLES;

public class User {
    private Long userId;
    private String userName;
    private ROLES userRole;
    private String userAvatar;

    public User(String userName, Long userId, ROLES userRole, String userAvatar) {
        this.userName = userName;
        this.userId = userId;
        this.userRole = userRole;
        this.userAvatar = userAvatar;
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

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatarId(String userAvatarId) {
        this.userAvatar = userAvatar;
    }

    public User(Long userId, String userName, ROLES userRole, String userAvatar) {
        this.userId = userId;
        this.userName = userName;
        this.userRole = userRole;
        this.userAvatar = userAvatar;
    }
}
