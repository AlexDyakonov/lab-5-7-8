package server.sql;

import server.exception.FileException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import static client.ui.ConsoleColors.error;
import static server.services.LoggerManager.setupLogger;
import static util.Message.getLog;

/**
 * The type Properties provider.
 */
public class PropertiesProvider {
    private static final Logger logger = Logger.getLogger(PropertiesProvider.class.getName());
    private static final Properties appProperties = new Properties();

    static {
        setupLogger(logger);
        try {
            logger.info(getLog("prop_provider_loading"));
            appProperties.load(new FileInputStream("src/main/resources/application.properties"));
            logger.info(getLog("prop_provider_finish"));
        } catch (IOException e) {
            logger.severe(e.getMessage());
            throw new FileException(error("Не найден файл application.properties. Для работы программы необходимо создать соответствующий файл."));
        }
    }

    /**
     * Gets app properties.
     *
     * @return the app properties
     */
    public static Properties getAppProperties() {
        return new Properties(appProperties); //Defence copy, изменяя ее, исходный файл не будет изменяться
    }
}