package server.authentication;


import server.db.SQLDataBaseProvider;

import java.util.logging.Logger;

public class Authentication {
    private static final Logger logger = Logger.getLogger(Authentication.class.getName());
    private final SQLDataBaseProvider sqlDataBaseProvider;

    public Authentication(SQLDataBaseProvider sqlDataBaseProvider) {
        this.sqlDataBaseProvider = sqlDataBaseProvider;
    }

//    public void registerUser(String username, String password) {
//        if (sqlDataBaseProvider.getUserNameList().contains(username)) {
//            System.out.println("Username already exists");
//        } else {
//            sqlDataBaseProvider.register(username, password);
//            System.out.println("Registration successful");
//        }
//    }
//
//    public boolean login(String username, String password) {
//        if (users.containsKey(username) && users.get(username).equals(password)) {
//            System.out.println("Login successful");
//            return true;
//        } else {
//            System.out.println("Invalid username or password");
//            return false;
//        }
//    }

    public SQLDataBaseProvider getSqlDataBaseProvider() {
        return sqlDataBaseProvider;
    }
}
