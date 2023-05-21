package ru.home.app.gui.controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ru.home.app.gui.utility.SpecialWindows;
import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.authentication.ROLES;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.exception.ApplicationException;
import ru.home.app.server.model.Car;
import ru.home.app.server.model.Coordinates;
import ru.home.app.server.model.dto.HumanBeingResponseDTOwithUsers;
import ru.home.app.util.LANGUAGE;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {
    private final HumanController controller;
    private final CurrentUserManager userManager;
    private final double width;
    private final double height;
    ObservableList<HumanBeingResponseDTOwithUsers> humanBeingResponseDTOwithUsersObservableList = FXCollections.observableArrayList();
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
    @FXML
    private Button close_button;
    @FXML
    private TableView<HumanBeingResponseDTOwithUsers> table;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, Long> column_id;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, String> column_name;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, String> column_creator;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, Integer> column_x;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, Double> column_y;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, String> column_creation_time;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, String> column_hero;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, String> column_toothpick;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, Float> column_speed;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, String> column_soundtrack;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, String> column_weapon;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, String> column_mood;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, String> column_car_name;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, String> column_car_cool;
    @FXML
    private TextField sb_find_by_name;
    @FXML
    private Button button_info;
    @FXML
    private Label label_users;
    @FXML
    private Label label_humanbeing;
    @FXML
    private Label label_all;

    @FXML
    private MenuButton mb_delete;
    @FXML
    private MenuItem mi_delete_by_id;
    @FXML
    private MenuItem mi_clear;
    @FXML
    private MenuItem mi_clear_all;

    @FXML
    private Label label_error_msg;

    public LoggedInController(double width, double height, CurrentUserManager userManager, HumanController controller) {
        this.userManager = userManager;
        this.controller = controller;
        this.width = width;
        this.height = height;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ru/home/app/main-page.fxml"));
        fxmlLoader.setController(this);
        try {
            parent = fxmlLoader.load();
            scene = new Scene(parent, this.width, this.height);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    public void infoButtonOnAction(ActionEvent e) {
        label_users.setText(String.valueOf(controller.getUserNameList().size()));
        label_humanbeing.setText(String.valueOf(controller.getAllHuman().size()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<HumanBeingResponseDTOwithUsers> allHuman = controller.getAllHumanWithUsers();

        label_all.setText("All(" + allHuman.size() + ")");

        humanBeingResponseDTOwithUsersObservableList.addAll(allHuman);
        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        column_creator.setCellValueFactory(new PropertyValueFactory<>("username"));
        column_x.setCellValueFactory(cellData -> {
            Coordinates coordinates = cellData.getValue().getCoordinates();
            Integer value = coordinates.getX();
            return new SimpleIntegerProperty(value).asObject();
        });
        column_y.setCellValueFactory(cellData -> {
            Coordinates coordinates = cellData.getValue().getCoordinates();
            Double value = coordinates.getY();
            return new SimpleDoubleProperty(value).asObject();
        });
        column_creation_time.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        column_hero.setCellValueFactory(new PropertyValueFactory<>("realHero"));
        column_toothpick.setCellValueFactory(new PropertyValueFactory<>("hasToothpick"));
        column_speed.setCellValueFactory(new PropertyValueFactory<>("impactSpeed"));
        column_soundtrack.setCellValueFactory(cellData -> {
            try {
                String song = cellData.getValue().getSoundtrackName();
                return new SimpleStringProperty(song);
            } catch (NullPointerException e) {
                return new SimpleStringProperty("null");
            }
        });
        column_weapon.setCellValueFactory(new PropertyValueFactory<>("weaponType"));
        column_mood.setCellValueFactory(new PropertyValueFactory<>("mood"));
        column_car_name.setCellValueFactory(cellData -> {
            try {
                Car car = cellData.getValue().getCar();
                String value = car.getName();
                return new SimpleStringProperty(value);
            } catch (NullPointerException e) {
                return new SimpleStringProperty("null");
            }
        });
        column_car_cool.setCellValueFactory(cellData -> {
            try {
                Car car = cellData.getValue().getCar();
                boolean cool = car.isCool();
                return new SimpleStringProperty(String.valueOf(cool));
            } catch (NullPointerException e) {
                return new SimpleStringProperty("null");
            }
        });

        table.setItems(humanBeingResponseDTOwithUsersObservableList);

        FilteredList<HumanBeingResponseDTOwithUsers> filteredData = new FilteredList<>(humanBeingResponseDTOwithUsersObservableList, b -> true);
        sb_find_by_name.textProperty().addListener((observable, oldvalue, newValue) -> {
            filteredData.setPredicate(humanBeingResponseDTOwithUsers -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                return humanBeingResponseDTOwithUsers.getName().toLowerCase().contains(searchKeyword);
            });
        });

        SortedList<HumanBeingResponseDTOwithUsers> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);

        mi_delete_by_id.setOnAction(e -> SpecialWindows.deleteWindow(controller, LANGUAGE.EN, this));
        mi_clear.setOnAction(event -> {
            if (SpecialWindows.showConfirmationDialog("Are confirm deleting all your humanbeings?")) {
                try {
                    controller.clear();
                    label_error_msg.setText("");
                } catch (ApplicationException e) {
                    label_error_msg.setText("Elements was not deleted.");
                }
            }
        });
        mi_clear_all.setOnAction(event -> {
            if (userManager.getUserRole().equals(ROLES.ADMIN)) {
                try {
                    controller.clearAll();
                    label_error_msg.setText("");
                } catch (ApplicationException e) {
                    label_error_msg.setText("Elements was not deleted.");
                }
            }
            if (!userManager.getUserRole().equals(ROLES.ADMIN)) {
                SpecialWindows.showInfoMessage("Must be admin to clear all.");
            }
        });
    }

    public void addHumanToTable(HumanBeingResponseDTOwithUsers human) {
        humanBeingResponseDTOwithUsersObservableList.add(human);
    }

    public void removeHumanFromTable(long id) {
        humanBeingResponseDTOwithUsersObservableList.removeIf(p -> p.getId().equals(id));
    }

    public void setUserInfo(String username, String userRole) {
        label_nickname.setText(username);
        label_role.setText(userRole);
    }

    public void logoutButtonOnAction() {
        userManager.clear();
        new LoginController(width, height, userManager, controller).launchLoginScene(stage);
    }

    public void addNewButtonOnAction() {
        configBeforeAdd();
        new AddUserController(controller, userManager, 600, 600).launchAddScene(new Stage(), this);
    }

    public void launchMainScene(Stage stage) {
        this.stage = stage;
        label_role.setText(userManager.getUserRole().toString());
        label_nickname.setText(userManager.getUserName());
        String avatarPath = "/ru/home/app/assets/avatars/" + userManager.getUserAvatar() + ".jpg";
        try {
            System.out.println(userManager.getUserAvatar());
            im_avatar.setImage(new Image(LoggedInController.class.getResource(avatarPath).toURI().toString()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);

        stage.hide();
        stage.show();
    }

    public void configBeforeAdd() {
        GaussianBlur blur = new GaussianBlur();
        blur.setRadius(10);
        scene.getRoot().setEffect(blur);
    }

    public void configAfterAdd() {
        scene.getRoot().setEffect(null);
    }
}
