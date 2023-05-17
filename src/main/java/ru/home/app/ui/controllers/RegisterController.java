package ru.home.app.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.controller.GuiHumanController;
import ru.home.app.server.controller.GuiHumanControllerImpl;
import ru.home.app.server.exception.AuthenticationException;
import ru.home.app.server.exception.ValidationException;
import ru.home.app.server.validation.Validation;

import java.io.IOException;

//TODO fix.
public class RegisterController {
    private final GuiHumanController humanController;
    private final CurrentUserManager userManager;
    private double width;
    private double height;
    private Parent parent;
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;
    @FXML
    private Label label_error_msg;

    public RegisterController(double width, double height, CurrentUserManager userManager, GuiHumanController controller) {
        this.userManager = userManager;
        this.humanController = controller;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ru/home/app/register-page.fxml"));
        fxmlLoader.setController(this);
        this.width = width;
        this.height = height;
        try {
            parent = (Parent) fxmlLoader.load();
            scene = new Scene(parent, width, height);
        } catch (IOException e) {
            //TODO обработать
        }
    }

    public void launchRegisterScene(Stage stage) {
        this.stage = stage;
        stage.setScene(scene);

        stage.hide();
        stage.show();
    }

    @FXML
    private Button close_button;

    public void closeButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button button_log_in;

    public void loginButtonOnAction(ActionEvent e) {
        new LoginController(width, height, userManager, humanController).launchLoginScene(stage);
    }

    @FXML
    private Button button_sign_up;

    private boolean checkFields() {
        if (!tf_username.getText().isBlank() && !pf_password.getText().isBlank()) {
            label_error_msg.setText("Attempt to register.");
            return true;
        }
        if (tf_username.getText().isBlank() || pf_password.getText().isBlank()) {
            label_error_msg.setText("You should insert username and password.");
        }
        return false;
    }

    public void signUpButtonOnAction(ActionEvent e) {
        try {
            humanController.userRegister(tf_username.getText(), pf_password.getText());
        } catch (ValidationException | AuthenticationException e1) {
            label_error_msg.setText(e1.getMessage());
        }
    }

    public GuiHumanController getHumanController() {
        return humanController;
    }

    public CurrentUserManager getUserManager() {
        return userManager;
    }
}
