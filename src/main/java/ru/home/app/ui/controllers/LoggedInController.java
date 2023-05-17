package ru.home.app.ui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.controller.GuiHumanController;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {
    private final GuiHumanController humanController;
    private final CurrentUserManager userManager;
    private double width;
    private double height;
    private Parent parent;
    private Stage stage;
    private Scene scene;
    @FXML
    private Button button_logout;
    @FXML
    private Button button_language;
    @FXML
    private Label label_nickname;
    @FXML
    private Label label_role;
    @FXML
    private ImageView im_avatar;

    public LoggedInController(double width, double height, CurrentUserManager userManager, GuiHumanController controller) {
        this.userManager = userManager;
        this.humanController = controller;
        this.width = width;
        this.height = height;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ru/home/app/main-page.fxml"));
        fxmlLoader.setController(this);
        try {
            parent = (Parent) fxmlLoader.load();
            scene = new Scene(parent, this.width, this.height);
            label_role.setText(userManager.getUserRole().toString());
            label_nickname.setText(userManager.getUserName());
            String avatarPath = "/ru/home/app/assets/avatars/" + userManager.getUserAvatar() + ".jpg";
            im_avatar.setImage(new Image(LoggedInController.class.getResource(avatarPath).toURI().toString()));
        } catch (IOException e) {
            //TODO обработать
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Button close_button;

    public void closeButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }

    public void setUserInfo(String username, String userRole) {
        label_nickname.setText(username);
        label_role.setText(userRole);
    }

    public void launchMainScene(Stage stage) {
        this.stage = stage;
        stage.setScene(scene);

        stage.hide();
        stage.show();
    }
}
