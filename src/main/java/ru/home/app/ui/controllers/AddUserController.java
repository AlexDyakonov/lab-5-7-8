package ru.home.app.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.controller.GuiHumanController;
import ru.home.app.server.exception.ValidationException;
import ru.home.app.server.model.dto.HumanBeingRequestDTO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddUserController {
    private final GuiHumanController controller;
    private final CurrentUserManager userManager;
    private LoggedInController loggedInController;
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
    private Button sumbit_button;

    @FXML
    private Label label_error_msg;

    public AddUserController(GuiHumanController controller, CurrentUserManager userManager, double width, double height) {
        this.controller = controller;
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

    public void launchAddScene(Stage stage, LoggedInController loggedInController) {
        this.loggedInController = loggedInController;
        this.stage = stage;
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        stage.hide();
        stage.show();
    }

    @FXML
    private Button close_button;

    public void closeButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
        loggedInController.configAfterAdd();
    }

    public void sumbitButtonOnAction(ActionEvent e) {
        try {
            HumanBeingRequestDTO humanBeingRequestDTO = new HumanBeingRequestDTO();
            controller.createHuman(humanBeingRequestDTO);
        } catch (ValidationException ex) {
            label_error_msg.setText(ex.getMessage());
        }
    }
}
