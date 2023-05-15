package ru.home.app.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    private double width;
    private double height;
    private Parent parent;
    private Stage stage;
    private Scene scene;

    public RegisterController(double width, double height) {
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
        new LoginController(width, height).launchLoginScene(stage);
    }
}
