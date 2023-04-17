package server.commands;

import server.commands.list.*;
import server.controller.HumanController;
import server.controller.HumanControllerImpl;
import server.exception.*;
import server.services.BuilderType;
import server.services.HistoryManager;
import server.services.ScriptManager;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import static util.Message.getWarning;


public class Invoker {
    private static final Map<String, Command> commandsMap = new HashMap<>();
    private final ScriptManager scriptManager = new ScriptManager(null);
    private HistoryManager history;
    private String fileName;
    private HumanController controller;
    private BufferedReader cmdReader;
    private BufferedReader fileReader;
    private BuilderType builderType;
    private LANGUAGE language;


    public Invoker(String fileName, List<String> scriptHistory, BuilderType builderType, LANGUAGE language) {
        this.fileName = fileName;
        this.controller = new HumanControllerImpl(fileName);
        this.history = new HistoryManager(15); // limit history size
        this.cmdReader = cmdReader == null ? new BufferedReader(new InputStreamReader(System.in)) : cmdReader;
        this.builderType = builderType;
        this.language = language;
        controller.setLanguage(language);
        init();
    }

    public Invoker clearMap() {
        commandsMap.clear();
        return this;
    }

    public void init() {
        addCommand("help", new HelpCommand(language));
        addCommand("info", new InfoCommand(controller, language));
        addCommand("show", new ShowCommand(controller, language));
        addCommand("add", new AddCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("update", new UpdateCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("remove_by_id", new RemoveByIdCommand(controller, language));
        addCommand("clear", new ClearCommand(controller, language));
        addCommand("save", new SaveCommand(controller, fileName, language));
        addCommand("execute_script", new ExecuteScriptCommand(this, scriptManager, language));
        addCommand("exit", new ExitCommand(language));
        addCommand("add_if_max", new AddIfMaxCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("add_if_min", new AddIfMinCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("history", new HistoryCommand(history, language));
        addCommand("max_by_impact_speed", new MaxByImpactSpeedCommand(controller, language));
        addCommand("count_by_mood", new CountByMoodCommand(controller, language));
        addCommand("print_ascending", new PrintAscendingCommand(controller, language));
        addCommand("language", new LanguageCommand(this));
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
                System.out.println(getWarning("command_not_found", language));
            }
        } catch (ApplicationException | ArgumentException | CommandException | FileException | ValidationException e) {
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

    public Invoker setCmdReader(BufferedReader cmdReader) {
        this.cmdReader = cmdReader;
        return this;
    }

    public BufferedReader getFileReader() {
        return fileReader;
    }

    public Invoker setFileReader(BufferedReader fileReader) {
        this.fileReader = fileReader;
        return this;
    }

    public BuilderType getBuilderType() {
        return builderType;
    }

    public Invoker setBuilderType(BuilderType builderType) {
        this.builderType = builderType;
        return this;
    }

    public LANGUAGE getLanguage() {
        return language;
    }

    public void setLanguage(LANGUAGE language) {
        this.language = language;
        controller.setLanguage(language);
        init();
    }

    public HumanController getController() {
        return controller;
    }

}
