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
//        ConsoleUI session = new ConsoleUI(new Invoker(BuilderType.CMD, LANGUAGE.RU));
//        session.start();
        launch(args);
    }
}