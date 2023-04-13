package server.commands;

import server.commands.list.*;
import server.controller.HumanController;
import server.controller.HumanControllerImpl;
import server.services.BuilderType;
import server.services.HistoryManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Invoker {
    private HistoryManager history;
    private List<String> scriptHistory;
    private String fileName;
    private HumanController controller;
    private BufferedReader cmdReader;
    private BufferedReader fileReader;
    private BuilderType builderType;
    private static final Map<String, Command> commandsMap = new HashMap<>();


    public Invoker(String fileName, BufferedReader cmdReader, List<String> scriptHistory, BuilderType builderType) {
        this.controller = new HumanControllerImpl(fileName);
        this.scriptHistory = scriptHistory == null ? new ArrayList<>() : scriptHistory;
        this.history = new HistoryManager(15); // limit history size //TODO историю команд
        this.cmdReader = cmdReader == null ? new BufferedReader(new InputStreamReader(System.in)) : cmdReader;
        this.fileName = fileName;
        this.builderType = builderType;
        init();
    }

    private void init() {
        addCommand("add", new AddCommand(controller, cmdReader, fileReader, builderType));
        addCommand("help", new HelpCommand());
        addCommand("show", new ShowCommand(controller));
        addCommand("history", new HistoryCommand(history));
        addCommand("execute_script", new ExecuteScriptCommand(this));
    }

    public void execute(String input) {
        try {
            String[] commandArray = input.split(" ");
            String command = commandArray[0];
            for (Map.Entry<String, Command> pair : Invoker.getCommandsMap().entrySet()) {
                if (pair.getKey().equals(commandArray[0])) {
                    pair.getValue().execute(commandArray);
                    history.addCommandToHistory(command);
                }
            }
            if (!Invoker.getCommandsMap().containsKey(commandArray[0])) {
                System.out.println("Команды нет.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addCommand(String commandName, Command command) {
        commandsMap.put(commandName, command);
    }

    public static Map<String, Command> getCommandsMap() {
        return commandsMap;
    }

    public BufferedReader getCmdReader() {
        return cmdReader;
    }

    public void setCmdReader(BufferedReader cmdReader) {
        this.cmdReader = cmdReader;
    }

    public BufferedReader getFileReader() {
        return fileReader;
    }

    public void setFileReader(BufferedReader fileReader) {
        this.fileReader = fileReader;
    }

    public BuilderType getBuilderType() {
        return builderType;
    }

    public void setBuilderType(BuilderType builderType) {
        this.builderType = builderType;
    }

    public List<String> getScriptHistory() {
        return scriptHistory;
    }
}
