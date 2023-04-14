package server.services.builders;

import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;

public class LanguageBuilder {
    public static LANGUAGE getLanguage(LANGUAGE language, BufferedReader cmdReader) {
        try {
            System.out.println("Введите RU/EN для выбора языка системы: ");
            switch (cmdReader.readLine().toLowerCase()) {
                case "ru":
                    return LANGUAGE.RU;
                case "en":
                    return LANGUAGE.EN;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return language;
    }
}
