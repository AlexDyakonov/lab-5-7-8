package server.services;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static util.FileManager.createDirectory;
import static util.Message.getLog;

/**
 * The type Logger manager.
 */
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

    /**
     * Gets log file name.
     *
     * @param appName the app name
     * @return the log file name
     */
    private static String getLogFileName(String appName) {
        createDirectory("logs");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatter);
        return "logs\\" + appName + "_" + formattedDateTime + ".log";
    }

    /**
     * Sets logger.
     *
     * @param logger the logger
     */
    public static void setupLogger(Logger logger) {
        try {
            logger.addHandler(fileHandler);
            logger.setLevel(logger.getLevel());
        } catch (Exception e) {
            loggerManager.log(Level.SEVERE, "Error creating file handler", e);
        }
    }

    public static void logFunctionStartEnd(Runnable func, Logger logger, String fun1descr, String fun2descr) {
        logger.info(getLog(fun1descr));
        func.run();
        logger.info(getLog(fun2descr));
    }
}
