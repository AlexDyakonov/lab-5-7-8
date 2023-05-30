package ru.home.app.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ru.home.app.gui.utility.SpecialWindows;
import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.controller.HumanController;
import ru.home.app.util.language.LANGUAGE;
import ru.home.app.util.language.LocalizationManager;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static ru.home.app.gui.utility.SpecialWindows.showConfirmationDialog;
import static ru.home.app.util.Message.*;
import static ru.home.app.util.Message.getErrorMessagesGUI;
import static ru.home.app.util.Parser.fromStringToLanguage;

public class LoginController implements Initializable, Controller {
    private final CurrentUserManager userManager;
    private final HumanController controller;
    private final LocalizationManager localizationManager;
    private final double width;
    private final double height;
    private final Scene scene;
    private final Parent parent;
    private Stage stage;

    @FXML
    private Button button_login;
    @FXML
    private Button button_sign_up;
    @FXML
    private Button close_button;
    @FXML
    private Label label_error_msg;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;

    public LoginController(double width, double height, CurrentUserManager userManager, HumanController controller, LocalizationManager localizationManager) {
        this.localizationManager = localizationManager;
        this.userManager = userManager;
        this.controller = controller;
        this.width = width;
        this.height = height;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ru/home/app/login-page.fxml"));
        fxmlLoader.setController(this);
        LANGUAGE sysLang = (fromStringToLanguage(Locale.getDefault().getLanguage()));
        controller.setLanguage(sysLang);
        localizationManager.setLanguage(sysLang);
        try {
            parent = fxmlLoader.load();
            scene = new Scene(parent, this.width, this.height);
        } catch (Exception e) {
            SpecialWindows.showError(getErrorMessagesGUI("fxml_error", localizationManager.getLanguage()) + "\n" + e.getMessage(),
                    getErrorMessagesGUI("fxml_error_title", localizationManager.getLanguage()));
            throw new RuntimeException();
        }
    }

    public void launchLoginScene(Stage stage) {
        this.stage = stage;
        stage.setScene(scene);
        setLanguageInGui(localizationManager.getLanguage());

        stage.hide();
        stage.show();
    }


    private boolean checkFields() {
        LANGUAGE language = localizationManager.getLanguage();
        if (!tf_username.getText().isBlank() && !pf_password.getText().isBlank()) {
            label_error_msg.setText(getLoginMessagesGUI("login_attempt", language));
            return true;
        }
        if (tf_username.getText().isBlank() || pf_password.getText().isBlank()) {
            label_error_msg.setText(getLoginMessagesGUI("login_empty", language));
        }
        return false;
    }

    public void loginButtonOnAction(ActionEvent e) {
        LANGUAGE language = localizationManager.getLanguage();
        if (checkFields()) {
            String username = tf_username.getText();
            if (controller.checkUserPassword(username, pf_password.getText())) {
                userManager.configUserManager(username, controller);
                label_error_msg.setText(getLoginMessagesGUI("success", language));
                new MainPageController(width, height, userManager, controller, localizationManager).launchMainScene(stage);
            } else {
                label_error_msg.setText(getLoginMessagesGUI("login_invalid", language));
            }
        }
    }

    public void signUpButtonOnAction(ActionEvent e) {
        new RegisterController(width, height, userManager, controller, localizationManager).launchRegisterScene(stage);
    }

    public void closeButtonOnAction(ActionEvent e) {
        LANGUAGE language = localizationManager.getLanguage();
        if (showConfirmationDialog(getSpecialMessagesGUI("exit_message", language), language)) {
            Stage stage = (Stage) close_button.getScene().getWindow();
            stage.close();
        } else {
            stage.setAlwaysOnTop(false);
        }
    }



    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public CurrentUserManager getUserManager() {
        return userManager;
    }

    public HumanController getController() {
        return controller;
    }

    @FXML
    private MenuButton mb_language;
    @FXML
    private MenuItem mi_english;
    @FXML
    private MenuItem mi_russian;
    @FXML
    private MenuItem mi_belorussian;
    @FXML
    private MenuItem mi_spanish;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mi_english.setOnAction(e -> {
            String text = mi_english.getText();
            LANGUAGE language = fromStringToLanguage(text);
            mb_language.setText(text);
            localizationManager.setLanguage(language);
            setLanguageInGui(language);
        });
        mi_russian.setOnAction(e -> {
            String text = mi_russian.getText();
            LANGUAGE language = fromStringToLanguage(text);
            mb_language.setText(text);
            localizationManager.setLanguage(language);
            setLanguageInGui(language);
        });
        mi_belorussian.setOnAction(e -> {
            String text = mi_belorussian.getText();
            LANGUAGE language = fromStringToLanguage(text);
            mb_language.setText(text);
            localizationManager.setLanguage(language);
            setLanguageInGui(language);
        });
        mi_spanish.setOnAction(e -> {
            String text = mi_spanish.getText();
            LANGUAGE language = fromStringToLanguage(text);
            mb_language.setText(text);
            localizationManager.setLanguage(language);
            setLanguageInGui(language);
        });
    }

    private void setLanguageInGui(LANGUAGE language) {
        Map<String, Label> labels = new HashMap<>();
        LocalizationManager.collectLabels(parent, labels);
        for (Map.Entry<String, Label> entry : labels.entrySet()) {
            entry.getValue().setText(getLoginMessagesGUI(entry.getKey(), language));
        }
        mb_language.setText(getLoginMessagesGUI(mb_language.getId(), language));
        tf_username.setPromptText(getLoginMessagesGUI(tf_username.getId(), language));
        pf_password.setPromptText(getLoginMessagesGUI(pf_password.getId(), language));
        button_login.setText(getLoginMessagesGUI(button_login.getId(), language));
        button_sign_up.setText(getLoginMessagesGUI(button_sign_up.getId(), language));
    }
}
