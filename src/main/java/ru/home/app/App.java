package ru.home.app;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.home.app.gui.GraphicUI;
import ru.home.app.gui.controllers.LoginController;
import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.commands.Invoker;
import ru.home.app.server.controller.HumanControllerImpl;
import ru.home.app.server.services.builders.BuilderType;
import ru.home.app.ui.ConsoleUI;
import ru.home.app.util.language.LANGUAGE;
import ru.home.app.util.language.LocalizationManager;

import java.io.IOException;
import java.util.Locale;

import static ru.home.app.util.Parser.fromStringToLanguage;


public class App extends Application {
    public static void main(String[] args) {
        try {
            if (args[0].equals("-g")) {
                launch();
            }
            if (args[0].equals("-c")) {
                ConsoleUI session = new ConsoleUI(new Invoker(BuilderType.CMD, fromStringToLanguage(Locale.getDefault().getLanguage())));
                session.start();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Use flag to launch launch: -g - GUI, -c - CMD");
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        GraphicUI session = new GraphicUI();
        session.start(stage);
    }
}