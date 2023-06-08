package ru.home.app.gui;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.home.app.gui.controllers.LoginController;
import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.controller.HumanControllerImpl;
import ru.home.app.util.language.LANGUAGE;
import ru.home.app.util.language.LocalizationManager;

import java.util.Locale;

import static ru.home.app.util.Parser.fromStringToLanguage;

public class GraphicUI {
    public void start(Stage stage){
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        CurrentUserManager userManager = new CurrentUserManager();
        LANGUAGE sysLang = (fromStringToLanguage(Locale.getDefault().getLanguage()));
        HumanController controller = new HumanControllerImpl(userManager);
        LocalizationManager localizationManager = new LocalizationManager(controller, sysLang);

        new LoginController(1080, 768, userManager, controller, localizationManager).launchLoginScene(stage);
    }
}
