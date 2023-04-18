package server.services;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerManager {
    private static final Logger loggerManager = Logger.getLogger(LoggerManager.class.getName());
    private static final String LOG_FILE_NAME = "mylog.log";

    public static void setupLogger(Logger logger) {
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE_NAME);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (Exception e) {
            loggerManager.log(Level.SEVERE, "Error creating file handler", e);
        }
    }
}
