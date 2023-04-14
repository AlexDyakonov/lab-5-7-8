package server.commands;

import server.commands.list.*;
import server.controller.HumanController;
import server.controller.HumanControllerImpl;
import server.services.BuilderType;
import server.services.HistoryManager;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import static server.validation.Validation.validateFile;
import static util.Parser.tildaResolver;

public class Invoker {
    private static final Map<String, Command> commandsMap = new LinkedHashMap<>();
    private HistoryManager history;
    private List<String> scriptHistory;
    private String fileName;
    private HumanController controller;
    private BufferedReader cmdReader;
    private BufferedReader fileReader;
    private BuilderType builderType;
    private LANGUAGE language;


    public Invoker(String fileName, BufferedReader cmdReader, List<String> scriptHistory, BuilderType builderType, LANGUAGE language) {
        validateFile(fileName);
        this.fileName = tildaResolver(fileName);
        this.controller = new HumanControllerImpl(fileName);
        this.scriptHistory = scriptHistory == null ? new ArrayList<>() : scriptHistory;
        this.history = new HistoryManager(15); // limit history size
        this.cmdReader = cmdReader == null ? new BufferedReader(new InputStreamReader(System.in)) : cmdReader;
        this.builderType = builderType;
        this.language = language;
        init();
    }

    public void init() {
        commandsMap.clear();
        addCommand("help", new HelpCommand());
        addCommand("info", new InfoCommand(controller));
        addCommand("show", new ShowCommand(controller));
        addCommand("add", new AddCommand(controller, cmdReader, fileReader, builderType));
        addCommand("update", new UpdateCommand(controller, cmdReader, fileReader, builderType));
        addCommand("remove_by_id", new RemoveByIdCommand(controller));
        addCommand("clear", new ClearCommand(controller));
        addCommand("save", new SaveCommand(controller, fileName));
        addCommand("execute_script", new ExecuteScriptCommand(this)); //TODO fix bugs with recursion!!!
        addCommand("exit", new ExitCommand());
        addCommand("add_if_max", new AddIfMaxCommand(controller, cmdReader, fileReader, builderType));
        addCommand("add_if_min", new AddIfMinCommand(controller, cmdReader, fileReader, builderType));
        addCommand("history", new HistoryCommand(history));
        addCommand("max_by_impact_speed", new MaxByImpactSpeedCommand(controller));
        addCommand("count_by_mood", new CountByMoodCommand(controller));
        addCommand("print_ascending", new PrintAscendingCommand(controller));
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
    }

    public List<String> getScriptHistory() {
        return scriptHistory;
    }
}
