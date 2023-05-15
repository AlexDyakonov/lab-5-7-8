package ru.home.app.ui.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
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

    public LoginController(double width, double height) {
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

    public void signUpButtonOnAction(ActionEvent e) {
        new RegisterController(width, height).launchRegisterScene(stage);
    }

    public void closeButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
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
}
