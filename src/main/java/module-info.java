module ru.home.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.logging;
    requires org.json;
    requires java.sql;
    requires plexus.utils;
    requires javafx.media;

    opens ru.home.app to javafx.fxml;
    exports ru.home.app;
}