package ru.home.app.gui.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class SpecialWindows {
    public static boolean showConfirmationDialog(String message, Stage stage) {
        stage.setAlwaysOnTop(false);
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
}
