package ru.home.app.gui.utility;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.home.app.gui.controllers.MainPageController;
import ru.home.app.server.controller.HumanController;
import ru.home.app.util.LANGUAGE;

import java.util.Optional;

public class SpecialWindows {
    public static boolean showConfirmationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == yesButton;
    }

    public static void deleteWindow(HumanController controller, LANGUAGE language, MainPageController logged) {
        Stage window = new Stage();
        window.setAlwaysOnTop(true);
        window.setResizable(false);
        window.initStyle(StageStyle.UNDECORATED);
        window.setWidth(200);
        window.setHeight(150);


        // Create error label
        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");

        // Create text field
        TextField textField = new TextField();
        textField.setPromptText("Enter text here");

        // Create buttons
        Button confirmButton = new Button("CONFIRM");
        Button exitButton = new Button("EXIT");

        // Add button functionality
        confirmButton.setOnAction(e -> {
            String input = textField.getText();
            if (input.isEmpty()) {
                errorLabel.setText("Please enter ID");
            } else {
                try {
                    long id = Long.parseLong(textField.getText());
                    if (controller.deleteHumanById(id)) {
                        window.close();
                        logged.removeHumanFromTable(id);
                    } else {
                        errorLabel.setText("Not found or not yours.");
                    }
                } catch (NumberFormatException ex) {
                    errorLabel.setText("It should be number");
                }
            }
        });
        exitButton.setOnAction(e -> {
            window.close();
        });


        // Create layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(5));
        layout.getChildren().addAll(textField, confirmButton, exitButton, errorLabel);
        layout.setAlignment(Pos.CENTER);

        // Set scene and show window
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    public static void showInfoMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

