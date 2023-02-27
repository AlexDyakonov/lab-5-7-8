package ui;

import controller.HumanBeingControllerImpl;
import dao.HumanBeingDAOImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import services.HumanBeingServiceImpl;
import utility.ConsoleAsker;

/**
 * The type Console ui.
 */
public class ConsoleUI {

    private final HumanBeingControllerImpl userController = new HumanBeingControllerImpl(
        new HumanBeingServiceImpl(new HumanBeingDAOImpl()));

    /**
     * Start.
     */
    public void start() {
        try  (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println(MenuConstants.HELP);
            new CommandExecutor(new ConsoleAsker(), userController, reader).execute();
        } catch (IOException e) {
            System.out.println(e.getMessage()); //TODO посмотреть вывод ошибки, но вроде норм.
        }
    }
}
