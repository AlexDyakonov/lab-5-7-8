import client.ui.ConsoleUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import server.commands.Invoker;
import server.services.BuilderType;
import util.LANGUAGE;
import javafx.scene.control.Label;

/**
 * The type App.
 */
public class App extends Application {


    public static void main(String[] args) {
//        ConsoleUI session = new ConsoleUI(new Invoker(BuilderType.CMD, LANGUAGE.RU));
//        session.start();
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
}