package ru.home.app.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.home.app.gui.utility.SpecialWindows;
import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.exception.ValidationException;
import ru.home.app.server.model.Car;
import ru.home.app.server.model.Coordinates;
import ru.home.app.server.model.Mood;
import ru.home.app.server.model.WeaponType;
import ru.home.app.server.model.dto.HumanBeingRequestDTO;
import ru.home.app.server.validation.Validation;
import ru.home.app.util.Parser;
import ru.home.app.util.language.LANGUAGE;
import ru.home.app.util.language.LocalizationManager;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import static ru.home.app.server.validation.Validation.validate;
import static ru.home.app.util.Message.getAddMessagesGUI;
import static ru.home.app.util.Message.getErrorMessagesGUI;
import static ru.home.app.util.Parser.stringToMood;
import static ru.home.app.util.Parser.stringToWeaponType;

public class AddUserController implements Initializable {
    private final HumanController controller;
    private final CurrentUserManager userManager;
    private final double width = 424;
    private final double height = 652;
    private final String[] weapons = Parser.weaponToStringArray(LANGUAGE.EN);
    private final String[] moods = Parser.moodToStringArray(LANGUAGE.EN);
    private MainPageController mainPageController;
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
    @FXML
    private ChoiceBox<String> cb_mood;
    @FXML
    private Label label_mood;
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
    @FXML
    private Button close_button;
    private final LocalizationManager localizationManager;

    public AddUserController(HumanController controller, CurrentUserManager userManager, LocalizationManager localizationManager) {
        this.localizationManager = localizationManager;
        this.controller = controller;
        this.userManager = userManager;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ru/home/app/add-window.fxml"));
        fxmlLoader.setController(this);
        try {
            parent = fxmlLoader.load();
            scene = new Scene(parent, this.width, this.height);
        } catch (Exception e) {
            SpecialWindows.showError(getErrorMessagesGUI("fxml_error", localizationManager.getLanguage()) + "\n" + e.getMessage(),
                    getErrorMessagesGUI("fxml_error_title", localizationManager.getLanguage()));
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void launchAddScene(Stage stage, MainPageController mainPageController) {
        this.mainPageController = mainPageController;
        this.stage = stage;
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        setLanguageInGui(localizationManager.getLanguage());

        stage.hide();
        stage.show();
    }

    public void closeButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
        mainPageController.configAfterAdd();
    }

    private String getHumanNameFromTF(LANGUAGE language) {
        try {
            String name = tf_hb_name.getText();
            validate(name, Validation::validateString, getErrorMessagesGUI("name_not_null", language));
            return name;
        } catch (NullPointerException e) {
            throw new ValidationException(getErrorMessagesGUI("field_name_empty", language));
        }
    }

    private Coordinates getHumanCoordinatesFromTF(LANGUAGE language) {
        try {
            Coordinates coordinates = new Coordinates(tf_coord_x.getText(), tf_coord_y.getText(), language);
            validate(coordinates, Validation::validateCoordinates, getErrorMessagesGUI("coordinates_not_null", language));
            return coordinates;
        } catch (NullPointerException e) {
            throw new ValidationException(getErrorMessagesGUI("field_coordinates_empty", language));
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

    private String getCarNameFromTF(LANGUAGE language) {
        try {
            String name = tf_car_name.getText();
            validate(name, Validation::validateString, getErrorMessagesGUI("name_not_null", language));
            return name;
        } catch (NullPointerException e) {
            throw new ValidationException(getErrorMessagesGUI("field_car_empty", language));
        }
    }

    private Car getCarFromTF(LANGUAGE language) {
        return new Car(getCarNameFromTF(language), getCarCoolFromTF());
    }

    private Float getSpeedFromTF(LANGUAGE language) {
        try {
            return Float.parseFloat(tf_speed.getText());
        } catch (NumberFormatException e) {
            throw new ValidationException(getErrorMessagesGUI("speed_number", language));
        }
    }

    private Mood getMoodFromTF(LANGUAGE language) {
        try {
            return stringToMood(Parser.moodToStringArray(LANGUAGE.EN)
                    [IntStream.range(0, moods.length).filter(i -> Objects.equals(moods[i], cb_mood.getValue())).findFirst().orElse(-1)]);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            throw new ValidationException(getErrorMessagesGUI("field_mood_empty", language));
        }
    }

    private WeaponType getWeaponFromTF(LANGUAGE language) {
        try {
            return stringToWeaponType(Parser.weaponToStringArray(LANGUAGE.EN)
                    [IntStream.range(0, weapons.length).filter(i -> Objects.equals(weapons[i], cb_weapon.getValue())).findFirst().orElse(-1)]);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            throw new ValidationException(getErrorMessagesGUI("field_weapon_empty", language));
        }
    }

    private String getSoundtrackFromTF(LANGUAGE language) {
        try {
            String name = tf_song.getText();
            validate(name, Validation::validateString, getErrorMessagesGUI("song_not_null", language));
            return name;
        } catch (NullPointerException e) {
            throw new ValidationException(getErrorMessagesGUI("field_song_empty", language));
        }
    }

    private HumanBeingRequestDTO getHumanFromFields(LANGUAGE language) {
        HumanBeingRequestDTO humanBeingRequestDTO = new HumanBeingRequestDTO();
        humanBeingRequestDTO.setName(getHumanNameFromTF(language));
        humanBeingRequestDTO.setCoordinates(getHumanCoordinatesFromTF(language));
        humanBeingRequestDTO.setRealHero(getRealHeroFromTF());
        humanBeingRequestDTO.setHasToothpick(getHasToothpickFromTF());
        humanBeingRequestDTO.setImpactSpeed(getSpeedFromTF(language));
        humanBeingRequestDTO.setMood(getMoodFromTF(language));
        humanBeingRequestDTO.setWeaponType(getWeaponFromTF(language));
        humanBeingRequestDTO.setCar(getCarFromTF(language));
        humanBeingRequestDTO.setSoundtrackName(getSoundtrackFromTF(language));
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
        throw new ValidationException(getErrorMessagesGUI("adding_type_not_selected", localizationManager.getLanguage()));
    }

    public void sumbitButtonOnAction(ActionEvent e) {
        try {
            long id;
            LANGUAGE currLanguage = localizationManager.getLanguage();
            HumanBeingRequestDTO human = getHumanFromFields(currLanguage);
            switch (checkTypeOfAdd()) {
                case NONE -> {
                    id = controller.createHuman(human);
                    mainPageController.addHumanToTable(controller.getHumanWithUserById(id));
                }
                case ADD_IF_MAX -> {
                    id = controller.addIfMax(human);
                    if (id != -1L) {
                        mainPageController.addHumanToTable(controller.getHumanWithUserById(id));
                        mainPageController.configAfterAdd();
                        stage.close();
                    }
                    label_error_msg.setText(getErrorMessagesGUI("hb_not_added_max", currLanguage));
                }
                case ADD_IF_MIN -> {
                    id = controller.addIfMin(human);
                    if (id != -1L) {
                        mainPageController.addHumanToTable(controller.getHumanWithUserById(id));
                        mainPageController.configAfterAdd();
                        stage.close();
                    }
                    label_error_msg.setText(getErrorMessagesGUI("hb_not_added_min", currLanguage));
                }
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

    private void setLanguageInGui(LANGUAGE language) {
        Map<String, TextField> tfields = new HashMap<>();
        LocalizationManager.collectTextFields(parent, tfields);
        Map<String, Label> labels = new HashMap<>();
        LocalizationManager.collectLabels(parent, labels);
        for (Map.Entry<String, Label> entry : labels.entrySet()) {
            entry.getValue().setText(getAddMessagesGUI(entry.getKey(), language));
        }
        for (Map.Entry<String, TextField> entry : tfields.entrySet()) {
            entry.getValue().setPromptText(getAddMessagesGUI(entry.getKey(), language));
        }
        rb_none.setText(getAddMessagesGUI(rb_none.getId(), language));
        rb_if_max.setText(getAddMessagesGUI(rb_if_max.getId(), language));
        rb_if_min.setText(getAddMessagesGUI(rb_if_min.getId(), language));
        sumbit_button.setText(getAddMessagesGUI(sumbit_button.getId(), language));
    }
}
