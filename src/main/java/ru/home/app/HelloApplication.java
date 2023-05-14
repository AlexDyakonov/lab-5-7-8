package ru.home.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.home.app.server.commands.Invoker;
import ru.home.app.server.services.BuilderType;
import ru.home.app.ui.ConsoleUI;
import ru.home.app.ui.controllers.LoginController;
import ru.home.app.util.LANGUAGE;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        new LoginController(720, 512).launchLoginScene(stage);
    }

    public static void main(String[] args) {
        try {
            if (args[0].equals("-g")) {
                launch();
            }
            if (args[0].equals("-c")) {
                ConsoleUI session = new ConsoleUI(new Invoker(BuilderType.CMD, LANGUAGE.RU));
                session.start();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Use flag to launch launch: -g - GUI, -c - CMD");
        }
    }
}