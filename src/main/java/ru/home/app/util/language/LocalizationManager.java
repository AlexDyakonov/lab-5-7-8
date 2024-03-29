package ru.home.app.util.language;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.controller.HumanControllerImpl;

import java.util.HashMap;
import java.util.Map;

public class LocalizationManager {
    private final HumanController controller;
    private LANGUAGE language;

    public LocalizationManager(HumanController controller, LANGUAGE language) {
        this.language = language;
        this.controller = controller;
    }

    public static Map<String, Label> collectLabels(Node node, Map<String, Label> labelMap) {
        if (node instanceof Label label) {
            String fxId = label.getId();
            if (fxId != null) {
                labelMap.put(fxId, label);
            }
        } else if (node instanceof Parent parent) {
            for (Node child : parent.getChildrenUnmodifiable()) {
                collectLabels(child, labelMap);
            }
        }
        return labelMap;
    }

    public static Map<String, TableColumn<?, ?>> getTableColumns(TableView<?> tableView) {

        Map<String, TableColumn<?, ?>> columnMap = new HashMap<>();
        for (TableColumn<?, ?> column : tableView.getColumns()) {
            String fxId = column.getId();
            if (fxId != null) {
                columnMap.put(fxId, column);
            }
        }

        return columnMap;
    }

    public static void collectTextFields(Node node, Map<String, TextField> textFieldMap) {
        if (node instanceof TextField textField) {
            String fxId = textField.getId();
            if (fxId != null) {
                textFieldMap.put(fxId, textField);
            }
        } else if (node instanceof Parent parent) {
            for (Node child : parent.getChildrenUnmodifiable()) {
                collectTextFields(child, textFieldMap);
            }
        }
    }

    public LANGUAGE getLanguage() {
        return language;
    }

    public void setLanguage(LANGUAGE language) {
        this.language = language;
        controller.setLanguage(language);
    }
}
