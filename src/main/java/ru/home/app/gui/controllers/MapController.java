package ru.home.app.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.home.app.gui.utility.StickMan;
import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.controller.HumanController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MapController implements Initializable {
    private final HumanController controller;
    private final CurrentUserManager userManager;
    private final double width;
    private final double height;
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
    private Pane pane_map;
    @FXML
    private AnchorPane ap_main;


    public MapController(double width, double height, CurrentUserManager userManager, HumanController controller) {
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
            im_avatar.setImage(new Image(LoggedInController.class.getResource(avatarPath).toURI().toString()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);

        stage.hide();
        stage.show();
    }

    private List<StickMan> createListOfStickMans() {
        double MAP_WIDTH = pane_map.getWidth();
        final double MAP_HEIGHT = pane_map.getHeight();

        List<StickMan> stickManList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            StickMan stickMan = new StickMan();
            double x = 20 + Math.random() * MAP_WIDTH % (MAP_WIDTH - 20);
            double y = 20 + Math.random() * MAP_HEIGHT % (MAP_HEIGHT - 20);
            stickMan.generate(x, y, 0.5);

            stickManList.add(stickMan);
        }
        return stickManList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double MAP_WIDTH = pane_map.getWidth();
        final double MAP_HEIGHT = pane_map.getHeight();

        List<StickMan> stickManList = new ArrayList<>();
        Group map = new Group();

        for (int i = 0; i < 10; i++) {
            StickMan stickMan = new StickMan();
            double x = 50 + Math.random() * MAP_WIDTH % (MAP_WIDTH - 50);
            double y = 50 + Math.random() * MAP_HEIGHT % (MAP_HEIGHT - 50);
            stickMan.generate(x, y, 0.5);
            map.getChildren().addAll(stickMan.getShapes());
            stickManList.add(stickMan);
        }

        pane_map.getChildren().addAll(map);

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

    public void closeButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }
}
