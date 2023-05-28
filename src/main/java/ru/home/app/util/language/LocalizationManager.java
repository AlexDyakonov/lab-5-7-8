package ru.home.app.util.language;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.util.Map;

public class LocalizationManager {
    public static Map<String, String> collectLabels(Node node, Map<String, String> labelMap){
        if (node instanceof Label label) {
            String fxId = label.getId();
            String text = label.getText();
            labelMap.put(fxId, text);
        } else if (node instanceof Parent parent) {
            for (Node child : parent.getChildrenUnmodifiable()) {
                collectLabels(child, labelMap);
            }
        }
        return labelMap;
    }
}
