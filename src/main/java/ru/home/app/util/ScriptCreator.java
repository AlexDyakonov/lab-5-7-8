package ru.home.app.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ScriptCreator {
    private static final String[] firstNames = {"Avery", "Bailey", "Cameron", "Dakota", "Elliot", "Finley", "Gale", "Harper", "Jordan", "Kendall", "Logan", "Morgan", "Noah", "Parker", "Quinn", "Riley", "Sawyer", "Taylor", "Tyler"};
    private static final String[] lastNames = {"Smith", "Johnson", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Wilson", "Anderson", "Thomas", "Jackson"};

    public static void createAddScript() {
        try {
            FileWriter writer = new FileWriter("add_script1");
            for (int i = 0; i < 25; i++) {
                writer.write("add\n");
                writer.write(firstNames[new Random().nextInt(firstNames.length)] + " " +
                        lastNames[new Random().nextInt(lastNames.length)] + "\n");
                writer.write(new Random().nextInt(50) + 18 + "\n");
                writer.write(new Random().nextInt(100) + "\n");
                writer.write(new Random().nextBoolean() ? "t\n" : "f\n");
                writer.write(new Random().nextBoolean() ? "t\n" : "f\n");
                writer.write(new Random().nextInt(2) + "\n");
                writer.write("Soundtrack\n");
                writer.write(new Random().nextInt(2) + 1 + "\n");
                writer.write(new Random().nextInt(2) + 1 + "\n");
                writer.write("CarName\n");
                writer.write(new Random().nextBoolean() ? "t\n" : "f\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
