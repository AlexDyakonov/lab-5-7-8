package ru.home.app.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.exception.ValidationException;
import ru.home.app.server.model.Car;
import ru.home.app.server.model.Coordinates;
import ru.home.app.server.model.Mood;
import ru.home.app.server.model.WeaponType;
import ru.home.app.server.model.dto.HumanBeingRequestDTO;
import ru.home.app.server.validation.Validation;
import ru.home.app.util.LANGUAGE;
import ru.home.app.util.Parser;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import static ru.home.app.server.validation.Validation.validate;
import static ru.home.app.util.Parser.stringToMood;
import static ru.home.app.util.Parser.stringToWeaponType;

public class AddUserController implements Initializable {
    private final HumanController controller;
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
    private ChoiceBox<String> cb_weapon;
    @FXML
    private Label label_weapon;
    private final String[] weapons = Parser.weaponToStringArray(LANGUAGE.EN);

    @FXML
    private ChoiceBox<String> cb_mood;
    @FXML
    private Label label_mood;
    private final String[] moods = Parser.moodToStringArray(LANGUAGE.EN);

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


    public AddUserController(HumanController controller, CurrentUserManager userManager, double width, double height) {
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

    private String getHumanNameFromTF() {
        try {
            String name = tf_hb_name.getText();
            validate(name, Validation::validateString, "Name null error");
            return name;
        } catch (NullPointerException e) {
            throw new ValidationException("Input fields name");
        }
    }

    private Coordinates getHumanCoordinatesFromTF() {
        try {
            Coordinates coordinates = new Coordinates(tf_coord_x.getText(), tf_coord_y.getText());
            validate(coordinates, Validation::validateCoordinates, "null error");
            return coordinates;
        } catch (NullPointerException e) {
            throw new ValidationException("Input fields coordinates");
        }
    }

    private boolean getRealHeroFromTF() {
        return rb_hero.isSelected();
    }

    private boolean getHasToothpickFromTF() {
        return rb_toothpick.isSelected();
    }

    private boolean getCarCoolFromTF() {
        return rb_car_cool.isSelected();
    }

    private String getCarNameFromTF() {
        try {
            String name = tf_car_name.getText();
            validate(name, Validation::validateString, "Name null error");
            return name;
        } catch (NullPointerException e) {
            throw new ValidationException("Input fields car");
        }
    }

    private Car getCarFromTF() {
        return new Car(getCarNameFromTF(), getCarCoolFromTF());
    }

    private Float getSpeedFromTF() {
        try {
            return Float.parseFloat(tf_speed.getText());
        } catch (NumberFormatException e) {
            throw new ValidationException("Speed Should be numbers");
        }
    }

    private Mood getMoodFromTF() {
        try {
            return stringToMood(Parser.moodToStringArray(LANGUAGE.EN)
                    [IntStream.range(0, moods.length).filter(i -> Objects.equals(moods[i], cb_mood.getValue())).findFirst().orElse(-1)]);
        } catch (NullPointerException e) {
            throw new ValidationException("Input fields mood");
        }
    }

    private WeaponType getWeaponFromTF() {
        try {
            return stringToWeaponType(Parser.weaponToStringArray(LANGUAGE.EN)[IntStream.range(0, weapons.length).filter(i -> Objects.equals(weapons[i], cb_weapon.getValue())).findFirst().orElse(-1)]);
        } catch (NullPointerException e) {
            throw new ValidationException("Input fields weapon");
        }
    }

    private String getSoundtrackFromTF() {
        try {
            String name = tf_song.getText();
            validate(name, Validation::validateString, "Name null error");
            return name;
        } catch (NullPointerException e) {
            throw new ValidationException("Input fields soundtrack");
        }
    }

    private HumanBeingRequestDTO getHumanFromFields() {
        HumanBeingRequestDTO humanBeingRequestDTO = new HumanBeingRequestDTO();
        humanBeingRequestDTO.setName(getHumanNameFromTF());
        humanBeingRequestDTO.setCoordinates(getHumanCoordinatesFromTF());
        humanBeingRequestDTO.setRealHero(getRealHeroFromTF());
        humanBeingRequestDTO.setHasToothpick(getHasToothpickFromTF());
        humanBeingRequestDTO.setImpactSpeed(getSpeedFromTF());
        humanBeingRequestDTO.setMood(getMoodFromTF());
        humanBeingRequestDTO.setWeaponType(getWeaponFromTF());
        humanBeingRequestDTO.setCar(getCarFromTF());
        humanBeingRequestDTO.setSoundtrackName(getSoundtrackFromTF());
        return humanBeingRequestDTO;
    }

    private AddType checkTypeOfAdd() {
        if (rb_none.isSelected()) {
            return AddType.NONE;
        }
        if (rb_if_min.isSelected()) {
            return AddType.ADD_IF_MIN;
        }
        if (rb_if_max.isSelected()) {
            return AddType.ADD_IF_MAX;
        }
        throw new ValidationException("You should choose type of adding.");
    }

    public void sumbitButtonOnAction(ActionEvent e) {
        try {
            if (checkTypeOfAdd() == AddType.NONE) {
                controller.createHuman(getHumanFromFields());
            }
        } catch (ValidationException ex) {
            label_error_msg.setText(ex.getMessage());
        }
    }

    public void getWeapon(ActionEvent event) {
        label_weapon.setText("");
    }

    public void getMood(ActionEvent event) {
        label_mood.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cb_mood.getItems().addAll(moods);
        cb_mood.setOnAction(this::getMood);

        cb_weapon.getItems().addAll(weapons);
        cb_weapon.setOnAction(this::getWeapon);
    }
}
