package ru.home.app.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.authentication.ROLES;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.exception.AuthenticationException;
import ru.home.app.server.exception.ValidationException;
import ru.home.app.util.language.LANGUAGE;
import ru.home.app.util.language.LocalizationManager;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static ru.home.app.util.Message.getRegisterMessagesGUI;
import static ru.home.app.util.Parser.fromStringToLanguage;

//TODO fix.
public class RegisterController implements Initializable {
    private final HumanController humanController;
    private final CurrentUserManager userManager;
    private final LocalizationManager localizationManager;
    private final double width;
    private final double height;
    private Parent parent;
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;
    @FXML
    private Label label_error_msg;
    @FXML
    private Label label_error_msg1;
    @FXML
    private RadioButton rb_ava1;
    @FXML
    private RadioButton rb_ava2;
    @FXML
    private RadioButton rb_ava3;
    @FXML
    private Button close_button;
    @FXML
    private Button button_log_in;
    @FXML
    private Button button_sign_up;

    public RegisterController(double width, double height, CurrentUserManager userManager, HumanController controller, LocalizationManager localizationManager) {
        this.localizationManager = localizationManager;
        this.userManager = userManager;
        this.humanController = controller;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ru/home/app/register-page.fxml"));
        fxmlLoader.setController(this);
        this.width = width;
        this.height = height;
        try {
            parent = fxmlLoader.load();
            scene = new Scene(parent, width, height);
        } catch (IOException e) {
            //TODO обработать
        }
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

    public void launchRegisterScene(Stage stage) {
        this.stage = stage;
        stage.setScene(scene);

        setLanguageInGui(localizationManager.getLanguage());

        stage.hide();
        stage.show();
    }

    public void setAvatarToUser(ActionEvent e) {
        if (rb_ava1.isSelected()) {
            userManager.setUserAvatar("ava1");
        } else if (rb_ava2.isSelected()) {
            userManager.setUserAvatar("ava2");
        } else if (rb_ava3.isSelected()) {
            userManager.setUserAvatar("ava3");
        }
    }

    public void closeButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction(ActionEvent e) {
        new LoginController(width, height, userManager, humanController, localizationManager).launchLoginScene(stage);
    }

    private boolean checkFields() {
        if (!tf_username.getText().isBlank() && !pf_password.getText().isBlank()) {
            return true;
        }
        if (tf_username.getText().isBlank() || pf_password.getText().isBlank()) {
            label_error_msg.setText("You should insert username and password.");
        }
        return false;
    }

    private boolean checkAvatars() {
        if (!rb_ava1.isSelected() && !rb_ava2.isSelected() && !rb_ava3.isSelected()) {
            label_error_msg1.setText("You cannot select no buttons.");
            return false;
        }
        return true;
    }

    public void signUpButtonOnAction(ActionEvent e) {
        try {
            if (checkFields()) {
                if (checkAvatars()) {
                    System.out.println(userManager);
                    System.out.println(tf_username.getText());
                    userManager.setUserName(tf_username.getText()).setUserRole(ROLES.USER)
                            .setUserId(humanController.userRegister(userManager, pf_password.getText()));
                    new MainPageController(width, height, userManager, humanController, localizationManager).launchMainScene(stage);
                }
            }
        } catch (ValidationException | AuthenticationException e1) {
            label_error_msg1.setText(e1.getMessage());
        }
    }

    public HumanController getHumanController() {
        return humanController;
    }

    public CurrentUserManager getUserManager() {
        return userManager;
    }

    private void setLanguageInGui(LANGUAGE language) {
        Map<String, Label> labels = new HashMap<>();
        LocalizationManager.collectLabels(parent, labels);
        for (Map.Entry<String, Label> entry : labels.entrySet()) {
            entry.getValue().setText(getRegisterMessagesGUI(entry.getKey(), language));
        }
        tf_username.setPromptText(getRegisterMessagesGUI(tf_username.getId(), language));
        pf_password.setPromptText(getRegisterMessagesGUI(pf_password.getId(), language));
        button_sign_up.setText(getRegisterMessagesGUI(button_sign_up.getId(), language));
        button_log_in.setText(getRegisterMessagesGUI(button_log_in.getId(), language));
    }
}
