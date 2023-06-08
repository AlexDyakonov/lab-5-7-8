package ru.home.app.gui.controllers;

import javafx.animation.AnimationTimer;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import ru.home.app.gui.utility.NewStickman;
import ru.home.app.gui.utility.SpecialWindows;
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
    private static final double STICKMAN_VELOCITY = 1.5;
    private double mouseX = 0;
    private double mouseY = 0;

    private double[] generateCoords() {
        double size = NewStickman.getStickmanSize();
        double min = 100;
        double randomX = min + (MAP_WIDTH - min) * Math.random();
        double randomY = min + (MAP_HEIGHT - min) * Math.random();
        if (randomX - size < 0 || randomX + size > MAP_WIDTH || randomY - size < 0 || randomY + size > MAP_HEIGHT) {
            return generateCoords();
        }
        return new double[]{randomX, randomY};
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label_all.setText("All(" + mainPageController.humanBeingResponseDTOwithUsersObservableList.size() + ")");

        List<NewStickman> stickManList = new ArrayList<>();


        Random random = new Random();
        int counter = 0;
        for (HumanBeingResponseDTOwithUsers human : humans) {
            if (counter++ > 100) {
                break;
            }
            double speedX = 1 + random.nextDouble() * 3;
            double speedY = 1 + random.nextDouble() * 3;
            double[] coords = generateCoords();

            NewStickman stickman = new NewStickman(coords[0], coords[1], speedX, speedY, human.getUsername());
            stickManList.add(stickman);
            pane_map.getChildren().add(stickman.getStickmanGroup());
        }

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (NewStickman stickman : stickManList) {
                    stickman.move(mouseX, mouseY, MAP_WIDTH, MAP_HEIGHT);
                }
            }
        };
        timer.start();

        pane_map.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            mouseX = event.getX();
            mouseY = event.getY();
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