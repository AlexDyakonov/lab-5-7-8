package ru.home.app;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.commands.Invoker;
import ru.home.app.server.services.builders.BuilderType;
import ru.home.app.ui.ConsoleUI;
import ru.home.app.ui.controllers.AddUserController;
import ru.home.app.util.LANGUAGE;

import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);
        CurrentUserManager userManager = new CurrentUserManager();
        new AddUserController(null, userManager).launchAddScene(stage);

//        new LoginController(1080, 768, userManager, new GuiHumanControllerImpl(userManager)).launchLoginScene(stage);
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