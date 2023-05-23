package ru.home.app.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ScriptCreator {
    public static void createAddScript() {
        try {
            FileWriter writer = new FileWriter("add_script");
            for (int i = 0; i < 300; i++) {
                writer.write("add\n");
                writer.write("John\n");
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
