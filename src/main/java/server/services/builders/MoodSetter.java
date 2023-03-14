package server.services.builders;

import server.model.Mood;
import server.model.WeaponType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MoodSetter {
    public static Mood setMood() {
        int number = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choice mood type: 1 = SORROW, 2 = GLOOM, 3 = APATHY, 4 = CALM, 5 = RAGE");
            number = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.out.println("ERROR");
        }
        switch (number) {
            case 1: return Mood.SORROW;
            case 2: return Mood.GLOOM;
            case 3: return Mood.APATHY;
            case 4: return Mood.CALM;
            case 5: return Mood.RAGE;
            default: return Mood.RAGE; // the best choice
        }
    }
}
