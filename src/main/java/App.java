import client.ui.ConsoleUI;

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
        String args0 = "~/src/main/resources/database.csv";
        ConsoleUI session = new ConsoleUI(args0);
        session.start();
    }
}