package server.authentication;

public class UserManager {
    private String userName;
    private ROLES userRole;

    public UserManager(String userName, ROLES role) {
        this.userName = userName;
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
}
