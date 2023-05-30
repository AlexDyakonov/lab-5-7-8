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
import ru.home.app.util.language.LANGUAGE;

import java.util.Optional;

import static ru.home.app.util.Message.getSpecialMessagesGUI;

public class SpecialWindows {
    public static boolean showConfirmationDialog(String message, LANGUAGE language) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(getSpecialMessagesGUI("confirmation_title", language));
        alert.setHeaderText(null);
        alert.setContentText(message);

        ButtonType yesButton = new ButtonType(getSpecialMessagesGUI("button_yes", language));
        ButtonType noButton = new ButtonType(getSpecialMessagesGUI("button_no", language));

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
        textField.setPromptText(getSpecialMessagesGUI("enter_id", language));

        // Create buttons
        Button confirmButton = new Button(getSpecialMessagesGUI("confirm", language));
        Button exitButton = new Button(getSpecialMessagesGUI("exit", language));

        // Add button functionality
        confirmButton.setOnAction(e -> {
            String input = textField.getText();
            if (input.isEmpty()) {
                errorLabel.setText(getSpecialMessagesGUI("enter_id_warning", language));
            } else {
                try {
                    long id = Long.parseLong(textField.getText());
                    if (controller.deleteHumanById(id)) {
                        window.close();
                        logged.removeHumanFromTable(id);
                    } else {
                        errorLabel.setText(getSpecialMessagesGUI("delete_error", language));
                    }
                } catch (NumberFormatException ex) {
                    errorLabel.setText(getSpecialMessagesGUI("id_error", language));
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

