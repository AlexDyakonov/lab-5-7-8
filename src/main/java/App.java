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
        ConsoleUI session = new ConsoleUI(args[0]);
        session.start();
    }
}