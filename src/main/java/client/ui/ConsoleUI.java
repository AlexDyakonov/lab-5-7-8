package client.ui;

import client.utility.ConsoleAsker;
import server.services.CommandExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The type Console client.client.ui.
 */
public class ConsoleUI {

    private final CommandExecutor commandExecutor;

    public ConsoleUI() {
        this.commandExecutor = new CommandExecutor(new ConsoleAsker());
    }

    public void start() {
        System.out.println(MenuConstants.HELP);

        while (true) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                commandExecutor.execute(reader.readLine());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
