package ru.home.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.home.app.server.commands.Invoker;
import ru.home.app.server.services.BuilderType;
import ru.home.app.ui.ConsoleUI;
import ru.home.app.util.LANGUAGE;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 512);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        ConsoleUI session = new ConsoleUI(new Invoker(BuilderType.CMD, LANGUAGE.RU));
//        session.start();
        launch();
    }
}