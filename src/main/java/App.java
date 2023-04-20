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
        try {
            ConsoleUI session = new ConsoleUI(args[0], new Invoker(args[0], BuilderType.CMD, LANGUAGE.RU));
            session.start();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(getError("no_args_main", LANGUAGE.RU));
        } catch (FileException e) {
            System.out.println(e.getMessage());
        }
    }
}