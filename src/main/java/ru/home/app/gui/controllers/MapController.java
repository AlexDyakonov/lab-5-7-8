package ru.home.app.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import ru.home.app.gui.utility.SpecialWindows;
import ru.home.app.gui.utility.StickMan;
import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.model.dto.HumanBeingResponseDTOwithUsers;
import ru.home.app.util.language.LANGUAGE;
import ru.home.app.util.language.LocalizationManager;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import static ru.home.app.util.Message.getErrorMessagesGUI;
import static ru.home.app.util.Message.getMapMessagesGUI;
import static ru.home.app.util.Parser.fromStringToLanguage;

public class MapController implements Initializable {
    private final HumanController controller;
    private final CurrentUserManager userManager;
    private final List<HumanBeingResponseDTOwithUsers> humans;
    private final double width;
    private final double height;
    private Parent parent;
    private Stage stage;
    private Scene scene;
    private final double MAP_WIDTH = 760;
    private final double MAP_HEIGHT = 560;


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
    private Pane pane_map;
    @FXML
    private Button button_table;
    private final MainPageController mainPageController;
    private final LocalizationManager localizationManager;

    public MapController(double width, double height, CurrentUserManager userManager, HumanController controller, List<HumanBeingResponseDTOwithUsers> humans, MainPageController mainPageController, LocalizationManager localizationManager) {
        this.localizationManager = localizationManager;
        this.humans = humans;
        this.userManager = userManager;
        this.controller = controller;
        this.width = width;
        this.height = height;
        this.mainPageController = mainPageController;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ru/home/app/map.fxml"));
        fxmlLoader.setController(this);
        try {
            parent = fxmlLoader.load();
            scene = new Scene(parent, this.width, this.height);
        } catch(Exception e){
            SpecialWindows.showError(getErrorMessagesGUI("fxml_error", localizationManager.getLanguage()) + "\n" + e.getMessage(),
                    getErrorMessagesGUI("fxml_error_title", localizationManager.getLanguage()));
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

        setLanguageInGui(localizationManager.getLanguage());
        setInfo();

        stage.hide();
        stage.show();
    }

    @FXML
    private Label label_all;
    @FXML
    private MenuButton mb_language;
    @FXML
    private MenuItem mi_english;
    @FXML
    private MenuItem mi_russian;
    @FXML
    private MenuItem mi_belorussian;
    @FXML
    private MenuItem mi_spanish;
    @FXML
    private AnchorPane ap_main;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label_all.setText("All(" + mainPageController.humanBeingResponseDTOwithUsersObservableList.size() + ")");

        List<StickMan> stickManList = new ArrayList<>();

        int counter = 0;
        for (HumanBeingResponseDTOwithUsers human : humans) {
            if (counter++ > 100) {
                break;
            }
            StickMan stickMan = new StickMan();
            double x = 40 + Math.random() * MAP_WIDTH % (MAP_WIDTH - 100);
            double y = 40 + Math.random() * MAP_HEIGHT % (MAP_HEIGHT - 100);
            stickMan.generate(x, y, 0.5, human.getUsername()).addToPane(pane_map);
            stickManList.add(stickMan);
        }

        pane_map.setOnMouseMoved(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            for (StickMan stickMan : stickManList) {
                double stickManX = stickMan.getX();
                double stickManY = stickMan.getY();
                double distance = Math.sqrt(Math.pow(mouseX - stickManX, 2) + Math.pow(mouseY - stickManY, 2));
                if (distance < 75 && mouseX >= 0 && mouseY >= 0 && mouseX <= MAP_WIDTH && mouseY <= MAP_HEIGHT) {
                    double dx = stickManX - mouseX;
                    double dy = stickManY - mouseY;
                    double angle = Math.atan2(dy, dx);
                    double newX = stickManX + 5 * Math.cos(angle);
                    double newY = stickManY + 5 * Math.sin(angle);
                    stickMan.move(newX, newY, MAP_WIDTH, MAP_HEIGHT);
                }
            }
        });
        setInfo();
        mi_english.setOnAction(e -> {
            String text = mi_english.getText();
            LANGUAGE language = fromStringToLanguage(text);
            mb_language.setText(text);
            localizationManager.setLanguage(language);
            setLanguageInGui(language);
            setInfo();
        });
        mi_russian.setOnAction(e -> {
            String text = mi_russian.getText();
            LANGUAGE language = fromStringToLanguage(text);
            mb_language.setText(text);
            localizationManager.setLanguage(language);
            setLanguageInGui(language);
            setInfo();
        });
        mi_belorussian.setOnAction(e -> {
            String text = mi_belorussian.getText();
            LANGUAGE language = fromStringToLanguage(text);
            mb_language.setText(text);
            localizationManager.setLanguage(language);
            setLanguageInGui(language);
            setInfo();
        });
        mi_spanish.setOnAction(e -> {
            String text = mi_spanish.getText();
            LANGUAGE language = fromStringToLanguage(text);
            mb_language.setText(text);
            localizationManager.setLanguage(language);
            setLanguageInGui(language);
            setInfo();
        });
        ap_main.setOnMouseClicked(e ->{
            Media sound = new Media(new File("src/main/resources/ru/home/app/sounds/on_click.mp3").toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        });
    }

    @FXML
    private Button button_info;
    @FXML
    private Label label_number_users;
    @FXML
    private Label label_nuber_humanbeing;

    public void infoButtonOnAction(ActionEvent e) {
        label_number_users.setText(String.valueOf(controller.getUserNameList().size()));
        label_nuber_humanbeing.setText(String.valueOf(mainPageController.humanBeingResponseDTOwithUsersObservableList.size()));
        label_all.setText(label_all.getText().replace("%num%", String.valueOf(mainPageController.humanBeingResponseDTOwithUsersObservableList.size())));
    }

    private void setInfo() {
        label_nuber_humanbeing.setText(String.valueOf(mainPageController.humanBeingResponseDTOwithUsersObservableList.size()));
        label_number_users.setText(String.valueOf(controller.getUserNameList().size()));
        label_all.setText(label_all.getText().replace("%num%", String.valueOf(mainPageController.humanBeingResponseDTOwithUsersObservableList.size())));
    }

    public void closeButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    public void tableButtonOnAction(ActionEvent e) {
        mainPageController.launchMainScene(stage);
    }

    @FXML
    private Button button_logout;

    public void logoutButtonOnAction() {
        userManager.clear();
        new LoginController(width, height, userManager, controller, localizationManager).launchLoginScene(stage);
    }

    private void setLanguageInGui(LANGUAGE language) {
        Map<String, Label> labels = new HashMap<>();
        LocalizationManager.collectLabels(parent, labels);
        for (Map.Entry<String, Label> entry : labels.entrySet()) {
            entry.getValue().setText(getMapMessagesGUI(entry.getKey(), language));
        }
        button_info.setText(getMapMessagesGUI(button_info.getId(), language));
        button_logout.setText(getMapMessagesGUI(button_logout.getId(), language));
        button_table.setText(getMapMessagesGUI(button_table.getId(), language));
        mb_language.setText(getMapMessagesGUI(mb_language.getId(), language));
    }
}