package server.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import static server.services.LoggerManager.setupLogger;
import static util.Message.getLog;


public class SQLConnection {
    private static final Logger logger = Logger.getLogger(SQLConnection.class.getName());

    static {
        setupLogger(logger);
    }

    private final Connection connection;

    public SQLConnection() {
        try {
            String url = PropertiesProvider.getAppProperties().getProperty("datasource.url");
            logger.info(getLog("prop_url_got"));
            String username = PropertiesProvider.getAppProperties().getProperty("datasource.username");
            logger.info(getLog("prop_username_got"));
            String password = PropertiesProvider.getAppProperties().getProperty("datasource.password");
            logger.info(getLog("prop_pass_got"));
            connection = DriverManager.getConnection(url, username, password);
            logger.info(getLog("connection_created"));
        } catch (SQLException e) {
            logger.severe("connection_not_created");
            logger.severe(e.getMessage());
            throw new RuntimeException();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
