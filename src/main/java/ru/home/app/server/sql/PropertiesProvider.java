package ru.home.app.server.sql;

import ru.home.app.server.exception.FileException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import static ru.home.app.server.services.LoggerManager.setupLogger;
import static ru.home.app.ui.ConsoleColors.error;
import static ru.home.app.util.Message.getLog;

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
            InputStream inputStream = PropertiesProvider.class.getClassLoader().getResourceAsStream("application.properties");
            appProperties.load(inputStream);
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