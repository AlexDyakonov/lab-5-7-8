import client.ui.ConsoleUI;
import server.exception.ApplicationException;
import server.exception.FileException;

import java.io.File;
import java.util.Scanner;

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
//        String args0 = "~/src/main/resources/database.csv";
        try {
            ConsoleUI session = new ConsoleUI(args[0]);
            session.start();
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Необходимо ввести название файла с базой данных при запуске программы.");
        } catch (FileException e) {
            System.out.println(e.getMessage());
        }
    }
}