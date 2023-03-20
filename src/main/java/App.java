import client.ui.ConsoleUI;

public class App {

    public static void main(String[] args) {
        String args0 = "src/main/resources/database.csv";
        ConsoleUI session = new ConsoleUI(args0);
        session.start();
    }
}