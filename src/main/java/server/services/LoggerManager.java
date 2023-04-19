package server.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerManager {
    private static final Logger loggerManager = Logger.getLogger(LoggerManager.class.getName());
    private static final String LOG_FILE_NAME = getLogFileName("lab-5");
    private static final FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler(LOG_FILE_NAME);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getLogFileName(String appName) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatter);
        return appName + "_" + formattedDateTime + ".log";
    }

    public static void setupLogger(Logger logger) {
        try {
            logger.addHandler(fileHandler);
            logger.setLevel(logger.getLevel());
        } catch (Exception e) {
            loggerManager.log(Level.SEVERE, "Error creating file handler", e);
        }
    }
}
