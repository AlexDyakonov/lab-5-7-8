package ru.home.app.util.language;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import ru.home.app.gui.controllers.Controller;

import java.util.HashMap;
import java.util.Map;

public class LocalizationManager {
    private LANGUAGE language;
    private final Map<Controller, LANGUAGE> controllerLanguageMap;

    //todo оптимизировать можно сделав контроллер манагер, который будет проверять изменился ли язык и подставлять нужную загруженную сцену.
    public LocalizationManager(LANGUAGE language) {
        this.language = language;
        controllerLanguageMap = new HashMap<>();
    }

    public void addControllerToMap(Controller controller) {
        controllerLanguageMap.put(controller, language);
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

    public LANGUAGE getLanguage() {
        return language;
    }

    public void setLanguage(LANGUAGE language) {
        this.language = language;
    }
}
