package ru.home.app.gui.controllers;

import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import ru.home.app.gui.utility.SpecialWindows;
import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.authentication.ROLES;
import ru.home.app.server.commands.Invoker;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.exception.ApplicationException;
import ru.home.app.server.exception.AuthenticationException;
import ru.home.app.server.exception.FileException;
import ru.home.app.server.exception.ValidationException;
import ru.home.app.server.mapper.HumanBeingMapper;
import ru.home.app.server.model.Coordinates;
import ru.home.app.server.model.Mood;
import ru.home.app.server.model.WeaponType;
import ru.home.app.server.model.dto.HumanBeingResponseDTOwithUsers;
import ru.home.app.server.services.builders.BuilderType;
import ru.home.app.util.LANGUAGE;
import ru.home.app.util.ScriptCreator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    private final HumanController controller;
    private final CurrentUserManager userManager;
    private final Invoker invoker;
    private final double width;
    private final double height;
    ObservableList<HumanBeingResponseDTOwithUsers> humanBeingResponseDTOwithUsersObservableList = FXCollections.observableArrayList();
    private List<HumanBeingResponseDTOwithUsers> dataList;
    private Parent parent;
    private Stage stage;
    private Scene scene;

    @FXML
    private Button button_language;
    @FXML
    private Label label_nickname;
    @FXML
    private Label label_role;
    @FXML
    private ImageView im_avatar;

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
    private TableColumn<HumanBeingResponseDTOwithUsers, Boolean> column_hero;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, Boolean> column_toothpick;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, Float> column_speed;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, String> column_soundtrack;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, WeaponType> column_weapon;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, Mood> column_mood;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, String> column_car_name;
    @FXML
    private TableColumn<HumanBeingResponseDTOwithUsers, Boolean> column_car_cool;
    @FXML
    private TextField sb_find_by_name;
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

    @FXML
    private Button button_map;

    public MainPageController(double width, double height, CurrentUserManager userManager, HumanController controller) {
        this.userManager = userManager;
        this.controller = controller;
        this.invoker = new Invoker(BuilderType.FILE, LANGUAGE.EN, controller);
        invoker.setUserManager(userManager);
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

    public void launchMainScene(Stage stage) {
        this.stage = stage;
        label_role.setText(userManager.getUserRole().toString());
        label_nickname.setText(userManager.getUserName());
        String avatarPath = "/ru/home/app/assets/avatars/" + userManager.getUserAvatar() + ".jpg";
        try {
            System.out.println(userManager.getUserAvatar());
            im_avatar.setImage(new Image(MainPageController.class.getResource(avatarPath).toURI().toString()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);

        stage.hide();
        stage.show();
    }

    public void updateTable() {
        humanBeingResponseDTOwithUsersObservableList.clear();

        dataList = controller.getAllHumanWithUsers();

        humanBeingResponseDTOwithUsersObservableList.addAll(dataList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
        table.setEditable(true);

        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));

        column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        column_name.setCellFactory(TextFieldTableCell.forTableColumn());
        column_name.setOnEditCommit(event -> {
            try {
                HumanBeingResponseDTOwithUsers newHuman = event.getRowValue();
                newHuman.setName(event.getNewValue());

                controller.updateHuman(HumanBeingMapper.fromResponseWithUserToRequest(newHuman), newHuman.getId());
                humanBeingResponseDTOwithUsersObservableList.stream().filter(p -> p.getId()
                        .equals(event.getRowValue().getId())).toList().get(0).setName(event.getNewValue());
            } catch (AuthenticationException | ValidationException e){
                label_error_msg.setText(e.getMessage());
                updateTable();
            }
        });

        column_creator.setCellValueFactory(new PropertyValueFactory<>("username"));

        column_x.setCellValueFactory(cellData -> {
            Coordinates coordinates = cellData.getValue().getCoordinates();
            Integer value = coordinates.getX();
            return new SimpleIntegerProperty(value).asObject();
        });
        column_x.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        column_x.setOnEditCommit(event -> {
            try {
                controller.updateHuman(HumanBeingMapper.fromResponseWithUserToRequest(event.getRowValue()), event.getRowValue().getId());
                updateTable();
            } catch (AuthenticationException | ValidationException e){
                label_error_msg.setText(e.getMessage());
                table.refresh();
            }
        });

        column_y.setCellValueFactory(cellData -> {
            Coordinates coordinates = cellData.getValue().getCoordinates();
            Double value = coordinates.getY();
            return new SimpleDoubleProperty(value).asObject();
        });
        column_y.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        column_y.setOnEditCommit(event -> {
            try {
                controller.updateHuman(HumanBeingMapper.fromResponseWithUserToRequest(event.getRowValue()), event.getRowValue().getId());
                updateTable();
            } catch (AuthenticationException | ValidationException e) {
                label_error_msg.setText(e.getMessage());
                table.refresh();
            }
        });

        column_creation_time.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

        column_hero.setCellValueFactory(cellData -> {
            try {
                return new SimpleBooleanProperty(cellData.getValue().getRealHero());
            } catch (NullPointerException e) {
                return new SimpleBooleanProperty(false);
            }
        });
        column_hero.setCellFactory(tc -> new TableCell<>() {
            private final CheckBox checkBox = new CheckBox();

            {
                checkBox.setAlignment(Pos.CENTER);
                checkBox.setOnAction(event -> {
                    HumanBeingResponseDTOwithUsers myObject = getTableView().getItems().get(getIndex());
                    myObject.setRealHero(checkBox.isSelected());
                    try {
                        controller.updateHuman(HumanBeingMapper.fromResponseWithUserToRequest(myObject), myObject.getId());
                        updateTable();
                    } catch (AuthenticationException | ValidationException e) {
                        label_error_msg.setText(e.getMessage());
                        table.refresh();
                    }
                });
            }

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    checkBox.setSelected(item);
                    setGraphic(checkBox);
                }
            }
        });


        column_toothpick.setCellValueFactory(cellData -> {
            try {
                return new SimpleBooleanProperty(cellData.getValue().getHasToothpick());
            } catch (NullPointerException e) {
                return new SimpleBooleanProperty(false);
            }
        });
        column_toothpick.setCellFactory(tc -> new TableCell<>() {
            private final CheckBox checkBox = new CheckBox();

            {
                checkBox.setAlignment(Pos.CENTER);
                checkBox.setOnAction(event -> {
                    HumanBeingResponseDTOwithUsers myObject = getTableView().getItems().get(getIndex());
                    myObject.setHasToothpick(checkBox.isSelected());
                    try {
                        controller.updateHuman(HumanBeingMapper.fromResponseWithUserToRequest(myObject), myObject.getId());
                        updateTable();
                    } catch (AuthenticationException | ValidationException e) {
                        label_error_msg.setText(e.getMessage());
                        table.refresh();
                    }
                });
            }

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    checkBox.setSelected(item);
                    setGraphic(checkBox);
                }
            }
        });

        column_speed.setCellValueFactory(new PropertyValueFactory<>("impactSpeed"));
        column_speed.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        column_speed.setOnEditCommit(event -> {
            try {
                controller.updateHuman(HumanBeingMapper.fromResponseWithUserToRequest(event.getRowValue()), event.getRowValue().getId());
                updateTable();
            } catch (AuthenticationException | ValidationException e) {
                label_error_msg.setText(e.getMessage());
                table.refresh();
            }
        });

        column_soundtrack.setCellValueFactory(cellData -> {
            try {
                String song = cellData.getValue().getSoundtrackName();
                return new SimpleStringProperty(song);
            } catch (NullPointerException e) {
                return new SimpleStringProperty("null");
            }
        });
        column_soundtrack.setCellFactory(TextFieldTableCell.forTableColumn());
        column_soundtrack.setOnEditCommit(event -> {
            try {
                HumanBeingResponseDTOwithUsers newHuman = event.getRowValue();
                newHuman.setSoundtrackName(event.getNewValue());

                controller.updateHuman(HumanBeingMapper.fromResponseWithUserToRequest(newHuman), newHuman.getId());
                humanBeingResponseDTOwithUsersObservableList.stream().filter(p -> p.getId()
                        .equals(event.getRowValue().getId())).toList().get(0).setSoundtrackName(event.getNewValue());
            } catch (AuthenticationException | ValidationException e) {
                label_error_msg.setText(e.getMessage());
                updateTable();
            }
        });


        column_weapon.setCellValueFactory(new PropertyValueFactory<>("weaponType"));
        column_weapon.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(WeaponType weaponType, boolean empty) {
                super.updateItem(weaponType, empty);
                if (empty || weaponType == null) {
                    setGraphic(null);
                } else {
                    MenuItem menuItem1 = new MenuItem(WeaponType.AXE.toString());
                    menuItem1.setOnAction(event -> ((MenuButton) getGraphic()).setText(menuItem1.getText()));

                    MenuItem menuItem2 = new MenuItem(WeaponType.BAT.toString());
                    menuItem2.setOnAction(event -> ((MenuButton) getGraphic()).setText(menuItem2.getText()));

                    MenuItem menuItem3 = new MenuItem(WeaponType.SHOTGUN.toString());
                    menuItem3.setOnAction(event -> ((MenuButton) getGraphic()).setText(menuItem3.getText()));

                    MenuButton menuButton = new MenuButton(weaponType.toString(), null, menuItem1, menuItem2, menuItem3);
                    menuButton.setOnAction(event -> {
                        HumanBeingResponseDTOwithUsers myObject = getTableView().getItems().get(getIndex());
                        myObject.setWeaponType(WeaponType.valueOf(((MenuButton) event.getSource()).getText()));
                        try {
                            controller.updateHuman(HumanBeingMapper.fromResponseWithUserToRequest(myObject), myObject.getId());
                            updateTable();
                        } catch (AuthenticationException | ValidationException e) {
                            label_error_msg.setText(e.getMessage());
                            updateTable();
                        }
                    });
                    setGraphic(menuButton);
                }
            }
        });
        column_mood.setCellValueFactory(new PropertyValueFactory<>("mood"));
        column_mood.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Mood mood, boolean empty) {
                super.updateItem(mood, empty);
                if (empty || mood == null) {
                    setGraphic(null);
                } else {
                    MenuItem menuItem1 = new MenuItem(Mood.RAGE.toString());
                    menuItem1.setOnAction(event -> ((MenuButton) getGraphic()).setText(menuItem1.getText()));

                    MenuItem menuItem2 = new MenuItem(Mood.CALM.toString());
                    menuItem2.setOnAction(event -> ((MenuButton) getGraphic()).setText(menuItem2.getText()));

                    MenuItem menuItem3 = new MenuItem(Mood.APATHY.toString());
                    menuItem3.setOnAction(event -> ((MenuButton) getGraphic()).setText(menuItem3.getText()));

                    MenuItem menuItem4 = new MenuItem(Mood.GLOOM.toString());
                    menuItem4.setOnAction(event -> ((MenuButton) getGraphic()).setText(menuItem4.getText()));

                    MenuItem menuItem5 = new MenuItem(Mood.SORROW.toString());
                    menuItem5.setOnAction(event -> ((MenuButton) getGraphic()).setText(menuItem5.getText()));

                    MenuButton menuButton = new MenuButton(mood.toString(), null, menuItem1, menuItem2, menuItem3, menuItem4, menuItem5);
                    menuButton.setOnAction(event -> {

                        HumanBeingResponseDTOwithUsers human = getTableView().getItems().get(getIndex());
                        human.setMood(Mood.valueOf(((MenuButton) event.getSource()).getText()));
                        try {
                            controller.updateHuman(HumanBeingMapper.fromResponseWithUserToRequest(human), human.getId());
                            updateTable();
                        } catch (AuthenticationException | ValidationException e) {
                            label_error_msg.setText(e.getMessage());
                            updateTable();
                        }
                    });
                    setGraphic(menuButton);
                }
            }
        });

        column_car_name.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getCar().getName());
            } catch (NullPointerException e) {
                return new SimpleStringProperty("null");
            }
        });
        column_car_name.setCellFactory(TextFieldTableCell.forTableColumn());
        column_car_name.setOnEditCommit(event -> {
            try {
                HumanBeingResponseDTOwithUsers newHuman = event.getRowValue();
                newHuman.getCar().setName(event.getNewValue());

                controller.updateHuman(HumanBeingMapper.fromResponseWithUserToRequest(newHuman), newHuman.getId());
                humanBeingResponseDTOwithUsersObservableList.stream().filter(p -> p.getId()
                        .equals(event.getRowValue().getId())).toList().get(0).getCar().setName(event.getNewValue());
            } catch (AuthenticationException | ValidationException e) {
                label_error_msg.setText(e.getMessage());
                updateTable();
            }
        });


        column_car_cool.setCellValueFactory(cellData -> {
            try {
                return new SimpleBooleanProperty(cellData.getValue().getCar().isCool());
            } catch (NullPointerException e) {
                return new SimpleBooleanProperty(false);
            }
        });

        column_car_cool.setCellFactory(tc -> new TableCell<>() {
            private final CheckBox checkBox = new CheckBox();

            {
                checkBox.setAlignment(Pos.CENTER);
                checkBox.setOnAction(event -> {
                    HumanBeingResponseDTOwithUsers myObject = getTableView().getItems().get(getIndex());
                    myObject.setHasToothpick(checkBox.isSelected());
                    try {
                        controller.updateHuman(HumanBeingMapper.fromResponseWithUserToRequest(myObject), myObject.getId());
                        updateTable();
                    } catch (AuthenticationException | ValidationException e) {
                        label_error_msg.setText(e.getMessage());
                        table.refresh();
                    }
                });
            }

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    checkBox.setSelected(item);
                    setGraphic(checkBox);
                }
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


        mi_delete_by_id.setOnAction(e -> {
            SpecialWindows.deleteWindow(controller, LANGUAGE.EN, this);
        });
        mi_clear.setOnAction(event -> {
            if (SpecialWindows.showConfirmationDialog("Are confirm deleting all your humanbeings?")) {
                try {
                    controller.clear();
                    label_error_msg.setText("");
                    updateTable();
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
                    updateTable();
                } catch (ApplicationException e) {
                    label_error_msg.setText("Elements was not deleted.");
                }
            }
            if (!userManager.getUserRole().equals(ROLES.ADMIN)) {
                SpecialWindows.showInfoMessage("Must be admin to clear all.");
            }
        });

        setInfo();
    }

    private void setInfo() {
        label_humanbeing.setText(String.valueOf(humanBeingResponseDTOwithUsersObservableList.size()));
        label_all.setText("All(" + humanBeingResponseDTOwithUsersObservableList.size() + ")");
        label_users.setText(String.valueOf(controller.getUserNameList().size()));
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

    public void executeButtonOnAction(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose a script file");
            try {
                invoker.getCommandsMapManager().getCommandsMap().get("execute_script")
                        .execute(new String[]{"execute", fileChooser.showOpenDialog(null).getAbsolutePath()});
            } catch (ApplicationException | FileException | ValidationException e) {
                label_error_msg.setText(e.getMessage());
            }
        } catch (Exception ignored) {
        }
        updateTable();
    }
    private boolean checkUser(){
        return false;
    }
    public void updateName(TableColumn.CellEditEvent editedCell){
        HumanBeingResponseDTOwithUsers selectedHuman = table.getSelectionModel().getSelectedItem();
        selectedHuman.setName(editedCell.getNewValue().toString());
        controller.updateHuman(HumanBeingMapper.fromResponseWithUserToRequest(selectedHuman), selectedHuman.getId());
    }

    @FXML
    private Button button_logout;

    public void logoutButtonOnAction() {
        userManager.clear();
        new LoginController(width, height, userManager, controller).launchLoginScene(stage);
    }

    @FXML
    private Button close_button;

    public void closeButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button button_info;
    @FXML
    private Label label_users;
    @FXML
    private Label label_humanbeing;

    public void infoButtonOnAction(ActionEvent e) {
        label_users.setText(String.valueOf(controller.getUserNameList().size()));
        label_humanbeing.setText(String.valueOf(humanBeingResponseDTOwithUsersObservableList.size()));
    }

    @FXML
    private Button button_create;

    public void createButtonOnAction(ActionEvent e) {
        ScriptCreator.createAddScript();
    }

    public void addNewButtonOnAction() {
        configBeforeAdd();
        new AddUserController(controller, userManager, 600, 600).launchAddScene(new Stage(), this);
    }


    public void configBeforeAdd() {
        GaussianBlur blur = new GaussianBlur();
        blur.setRadius(10);
        scene.getRoot().setEffect(blur);
    }

    public void mapButtonOnAction(ActionEvent e) {
        new MapController(width, height, userManager, controller, dataList, this).launchMainScene(stage);
    }

    public void configAfterAdd() {
        scene.getRoot().setEffect(null);
    }
}
