package client.ui;

import server.services.CommandExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * The type Console client.ui.
 */
public class ConsoleUI {

    private final String file;
    private final CommandExecutor executor;

    public ConsoleUI(String fileName) {
        this.file = fileName;
        this.executor = new CommandExecutor(fileName);
    }

    public void start() {
        System.out.println(MenuConstants.HELP);
        String command;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!Objects.equals(command = reader.readLine(), "exit")) {
                executor.executeCommand(command);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}