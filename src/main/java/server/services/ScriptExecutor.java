package server.services;

import client.utility.ConsoleAsker;
import server.controller.HumanBeingController;
import server.controller.HumanBeingControllerImpl;
import server.exception.FileException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ScriptExecutor {

    private final HumanBeingController userController;
    private List<String> previousFiles;
    private final CommandExecutor commandExecutor; // нужен для выполнения команд

    public ScriptExecutor(HumanBeingController userController, List<String> previousFiles, CommandExecutor commandExecutor) {
        this.userController = userController;
        this.previousFiles = previousFiles == null ? new ArrayList<>() : previousFiles;
        this.commandExecutor = commandExecutor;
    }

    public ScriptExecutor() {
        this.userController = new HumanBeingControllerImpl();
        this.commandExecutor = new CommandExecutor(new ConsoleAsker());
    }

    // основной метод, запускает выполнение скрипта
    public void executeScript(String fileName) {
        executeListCommand(readCommandFromFile(fileName));
    }

    // читает команды из файла
    private List<String> readCommandFromFile(String filename) {
        List<String> commandFromFile = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
            commandFromFile.add(reader.readLine());
        } catch (IOException e) {
            throw new FileException(filename + " file reading error");
        }
        return commandFromFile;
    }

    // выполняет команды из файла
    private void executeListCommand(List<String> listOfCommand) {
        for (String command : listOfCommand) {
            commandExecutor.execute(command);
        }
    }
}