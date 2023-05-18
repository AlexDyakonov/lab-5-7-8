package ru.home.app.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.controller.GuiHumanController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddUserController implements Initializable {
    private final GuiHumanController humanController;
    private final CurrentUserManager userManager;
    private final double width = 424;
    private final double height = 652;
    private Parent parent;
    private Stage stage;
    private Scene scene;

    @FXML
    private RadioButton rb_if_min;
    @FXML
    private RadioButton rb_if_max;
    @FXML
    private RadioButton rb_none;

    @FXML
    private TextField tf_hb_name;

    @FXML
    private TextField tf_coord_x;
    @FXML
    private TextField tf_coord_y;

    @FXML
    private TextField tf_speed;

    @FXML
    private TextField tf_song;

    @FXML
    private MenuButton mb_weapon;
    @FXML
    private MenuButton mb_mood;

    @FXML
    private TextField tf_car_name;

    @FXML
    private RadioButton rb_car_cool;
    @FXML
    private RadioButton rb_toothpick;
    @FXML
    private RadioButton rb_hero;

    @FXML
    private Button close_button;

    @FXML
    private Button sumbit_button;

    public AddUserController(GuiHumanController humanController, CurrentUserManager userManager) {
        this.humanController = humanController;
        this.userManager = userManager;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ru/home/app/add-window.fxml"));
        fxmlLoader.setController(this);
        try {
            parent = fxmlLoader.load();
            scene = new Scene(parent, this.width, this.height);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, e); //TODO setup
        }
    }

    public void launchAddScene(Stage stage) {
        this.stage = stage;
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.hide();
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void closeButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }
}
