package server.services.builders;

import server.exception.ValidationException;
import server.model.Mood;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static client.ui.ConsoleColors.*;

public class MoodSetter {
    public static Mood getMood(BufferedReader reader) {
        int number = 0;
        try {
            System.out.println(whiteStr("Выберите настроение: 1 = SORROW, 2 = GLOOM, 3 = APATHY, 4 = CALM, 5 = RAGE"));
            number = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e){
            throw new ValidationException(unsuccess("Введите числовые значения."), e);
        } catch (IOException e) {
            System.out.println(error("Ошибка BufferedReader."));
        }
        switch (number) {
            case 1: return Mood.SORROW;
            case 2: return Mood.GLOOM;
            case 3: return Mood.APATHY;
            case 4: return Mood.CALM;
            default: return Mood.RAGE;
        }
    }
    public static Mood setMood(BufferedReader reader) {
        try {
            return getMood(reader);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return setMood(reader);
        }
    }
}
