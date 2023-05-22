package ru.home.app.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.home.app.gui.utility.StickMan;
import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.model.dto.HumanBeingResponseDTOwithUsers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

    public MapController(double width, double height, CurrentUserManager userManager, HumanController controller, List<HumanBeingResponseDTOwithUsers> humans) {
        this.humans = humans;
        this.userManager = userManager;
        this.controller = controller;
        this.width = width;
        this.height = height;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ru/home/app/map.fxml"));
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

    @FXML
    private Label label_all;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label_all.setText("All(" + humans.size() + ")");

        List<StickMan> stickManList = new ArrayList<>();

        for (HumanBeingResponseDTOwithUsers human : humans) {
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
                if (distance < 50 && mouseX >= 0 && mouseY >= 0 && mouseX <= MAP_WIDTH && mouseY <= MAP_HEIGHT) {
                    double dx = stickManX - mouseX;
                    double dy = stickManY - mouseY;
                    double angle = Math.atan2(dy, dx);
                    double newX = stickManX + 5 * Math.cos(angle);
                    double newY = stickManY + 5 * Math.sin(angle);
                    stickMan.move(newX, newY, MAP_WIDTH, MAP_HEIGHT);
                }
            }
        });
    }

    @FXML
    private Button button_info;
    @FXML
    private Label label_users;
    @FXML
    private Label label_humanbeing;

    public void infoButtonOnAction(ActionEvent e) {
        label_users.setText(String.valueOf(controller.getUserNameList().size()));
        label_humanbeing.setText(String.valueOf(controller.getAllHuman().size()));
    }


    public void closeButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    public void tableButtonOnAction(ActionEvent e) {
        new MainPageController(width, height, userManager, controller).launchMainScene(stage);
    }

    @FXML
    private Button button_logout;

    public void logoutButtonOnAction() {
        userManager.clear();
        new LoginController(width, height, userManager, controller).launchLoginScene(stage);
    }
}
