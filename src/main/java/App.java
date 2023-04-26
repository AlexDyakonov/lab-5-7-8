import client.ui.ConsoleUI;
import server.commands.Invoker;
import server.exception.FileException;
import server.services.BuilderType;
import util.LANGUAGE;

import static util.Message.getError;

/**
 * The type App.
 */
public class App {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ConsoleUI session = new ConsoleUI(new Invoker(BuilderType.CMD, LANGUAGE.RU));
        session.start();
    }
}