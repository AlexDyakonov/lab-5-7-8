package server.authentication;

public class UserManager {
    private String userName;
    private Long userId;
    private ROLES userRole;

    public UserManager(String userName, Long userId, ROLES role) {
        this.userName = userName;
        this.userId = userId;
        this.userRole = role;
    }

    public UserManager() {
    }

    public String getUserName() {
        return userName;
    }

    public UserManager setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public ROLES getUserRole() {
        return userRole;
    }

    public UserManager setUserRole(ROLES userRole) {
        this.userRole = userRole;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
