package client.ui;

import server.exception.CommandException;
import server.exception.FileException;
import server.services.BuilderType;
import server.services.CommandExecutor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import static client.ui.ConsoleColors.error;
import static server.validation.Parser.tildaResolver;
import static server.validation.Validation.*;

/**
 * The type Console ui.
 */
public class ConsoleUI {

    private final String file;
    private final CommandExecutor executor;

    /**
     * Instantiates a new Console ui.
     *
     * @param fileName the file name
     */
    public ConsoleUI(String fileName) {
        validateFile(fileName);
        fileName = tildaResolver(fileName);
        this.file = fileName;
        this.executor = new CommandExecutor(fileName, null);
    }

    /**
     * Start.
     */
    public void start() {
        System.out.println(MenuConstants.HELLO + MenuConstants.HELP);
        String command;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!Objects.equals(command = reader.readLine(), "exit") && !Objects.equals(command, null)) {
                executor.executeCommand(command, reader, null, BuilderType.CMD);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}