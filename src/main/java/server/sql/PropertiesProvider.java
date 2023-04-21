package server.sql;

import server.exception.FileException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static client.ui.ConsoleColors.error;

public class PropertiesProvider {
    private static final Properties appProperties = new Properties();

    static {
        try {
            appProperties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            throw new FileException(error("Не найден файл application.properties. Для работы программы необходимо создать соответствующий файл."));
        }
    }

    public static Properties getAppProperties() {
        return new Properties(appProperties); //Defence copy, изменяя ее, исходный файл не будет изменяться
    }
}