package ui;

import controller.HumanBeingControllerImpl;
import dao.HumanBeingDAOImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import services.HumanBeingServiceImpl;
import utility.ConsoleAsker;

public class ConsoleUI {

    private final HumanBeingControllerImpl userController = new HumanBeingControllerImpl(
        new HumanBeingServiceImpl(new HumanBeingDAOImpl()));

    public void start() {
        System.out.println(MenuConstants.HELP);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        new CommandExecutor(new ConsoleAsker(), userController, reader).execute();
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
