import client.ui.ConsoleUI;
import client.ui.NewUI;
import server.commands.Invoker;
import server.exception.FileException;
import server.services.BuilderType;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
        String args0 = "src/main/resources/database.csv";
        try {
//            ConsoleUI session = new ConsoleUI(args[0]);
//            ConsoleUI session = new ConsoleUI(args0);
            NewUI session = new NewUI(args0, new Invoker(args0, null, null, BuilderType.CMD, LANGUAGE.RU));
            session.start();

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Необходимо ввести название файла с базой данных при запуске программы.");
        } catch (FileException e) {
            System.out.println(e.getMessage());
        }
    }
}