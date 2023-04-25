package server.sql;

import server.exception.ApplicationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLConnection {
    private final Connection connection;

    public SQLConnection() {
        try {
            String url = PropertiesProvider.getAppProperties().getProperty("datasource.url");
            String username = PropertiesProvider.getAppProperties().getProperty("datasource.username");
            String password = PropertiesProvider.getAppProperties().getProperty("datasource.password");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
//            throw new ApplicationException("Не удалось создать соединение");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
