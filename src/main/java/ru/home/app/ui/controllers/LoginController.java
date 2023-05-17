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
import ru.home.app.server.model.User;

import java.io.IOException;

public class LoginController {
    private final CurrentUserManager userManager;
    private final GuiHumanController controller;
    private double width;
    private double height;
    private Stage stage;
    private Scene scene;
    private Parent parent;
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

    public LoginController(double width, double height, CurrentUserManager userManager, GuiHumanController controller) {
        this.controller = controller;
        this.userManager = userManager;
        this.width = width;
        this.height = height;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ru/home/app/login-page.fxml"));
        fxmlLoader.setController(this);
        try {
            parent = (Parent) fxmlLoader.load();
            scene = new Scene(parent, this.width, this.height);
        } catch (IOException ex) {
            System.out.println("Error displaying login window");
            throw new RuntimeException(ex);
        }
    }

    public void launchLoginScene(Stage stage) {
        this.stage = stage;
        stage.setScene(scene);

        stage.hide();
        stage.show();
    }

    private boolean checkFields() {
        if (!tf_username.getText().isBlank() && !pf_password.getText().isBlank()) {
            label_error_msg.setText("Attempt to login.");
            return true;
        }
        if (tf_username.getText().isBlank() || pf_password.getText().isBlank()) {
            label_error_msg.setText("You should insert username and password.");
        }
        return false;
    }

    public void loginButtonOnAction(ActionEvent e) {
        if (checkFields()) {
            String username = tf_username.getText();
            if (controller.checkUserPassword(username, pf_password.getText())) {
                userManager.configUserManager(username, controller);
                label_error_msg.setText("Success!");
                new LoggedInController(width, height, userManager, controller).launchMainScene(stage);
            } else {
                label_error_msg.setText("Invalid username or password.");
            }
        }
    }

    public void signUpButtonOnAction(ActionEvent e) {
        new RegisterController(width, height, userManager, controller).launchRegisterScene(stage);
    }

    public void closeButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    private void setCurrentWidthToStage(Number number2) {
        stage.setWidth((double) number2);
        width = (double) number2;
    }

    private void setCurrentHeightToStage(Number number2) {
        stage.setHeight((double) number2);
        height = (double) number2;
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

    public GuiHumanController getController() {
        return controller;
    }
}
