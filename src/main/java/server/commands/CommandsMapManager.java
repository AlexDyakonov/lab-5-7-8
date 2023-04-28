package server.commands;

import server.authentication.ROLES;
import server.authentication.UserManager;
import server.commands.list.*;
import server.controller.HumanController;
import server.services.BuilderType;
import server.services.HistoryManager;
import server.services.ScriptManager;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

//TODO подчистить, можно сделать лучше
public class CommandsMapManager {
    private static final Map<String, Command> commandsMap = new HashMap<>();
    private final ROLES role;
    private Invoker invoker;
    private final ScriptManager scriptManager = new ScriptManager(null);
    private final HistoryManager history = invoker.getHistory();
    private final HumanController controller = invoker.getController();
    private BufferedReader cmdReader = invoker.getCmdReader();
    private BufferedReader fileReader = invoker.getFileReader();
    private BuilderType builderType = invoker.getBuilderType();
    private LANGUAGE language = invoker.getLanguage();

    public CommandsMapManager(ROLES role) {
        this.role = role;
    }

    public Map<String, Command> getCommandsMap() {
        switch (role) {
            case ADMIN -> {
                return adminMap();
            }
            case USER -> {
                return userMap();
            }
            case GUEST -> {
                return guestMap();
            }
            default -> {
                return guestMap();
            }
        }
    }


    public Map<String, Command> adminMap() {
        addCommand("help", new HelpCommand(language));
        addCommand("info", new InfoCommand(controller, language));
        addCommand("show", new ShowCommand(controller, language));
        addCommand("add", new AddCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("update", new UpdateCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("remove_by_id", new RemoveByIdCommand(controller, language));
        addCommand("clear", new ClearCommand(controller, language));
        addCommand("execute_script", new ExecuteScriptCommand(invoker, scriptManager, language));
        addCommand("exit", new ExitCommand(language));
        addCommand("add_if_max", new AddIfMaxCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("add_if_min", new AddIfMinCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("history", new HistoryCommand(history, language));
        addCommand("max_by_impact_speed", new MaxByImpactSpeedCommand(controller, language));
        addCommand("count_by_mood", new CountByMoodCommand(controller, language));
        addCommand("print_ascending", new PrintAscendingCommand(controller, language));
        addCommand("language", new LanguageCommand(invoker));
        return commandsMap;
    }

    public Map<String, Command> userMap() {
        addCommand("help", new HelpCommand(language));
        addCommand("info", new InfoCommand(controller, language));
        addCommand("show", new ShowCommand(controller, language));
        addCommand("add", new AddCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("update", new UpdateCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("remove_by_id", new RemoveByIdCommand(controller, language));
        addCommand("clear", new ClearCommand(controller, language));
        addCommand("execute_script", new ExecuteScriptCommand(invoker, scriptManager, language));
        addCommand("exit", new ExitCommand(language));
        addCommand("add_if_max", new AddIfMaxCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("add_if_min", new AddIfMinCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("history", new HistoryCommand(history, language));
        addCommand("max_by_impact_speed", new MaxByImpactSpeedCommand(controller, language));
        addCommand("count_by_mood", new CountByMoodCommand(controller, language));
        addCommand("print_ascending", new PrintAscendingCommand(controller, language));
        addCommand("language", new LanguageCommand(invoker));
        return commandsMap;
    }

    public Map<String, Command> guestMap() {
        addCommand("help", new HelpCommand(language));
        addCommand("info", new InfoCommand(controller, language));
        addCommand("show", new ShowCommand(controller, language));
        addCommand("exit", new ExitCommand(language));
        addCommand("history", new HistoryCommand(history, language));
        addCommand("max_by_impact_speed", new MaxByImpactSpeedCommand(controller, language));
        addCommand("count_by_mood", new CountByMoodCommand(controller, language));
        addCommand("print_ascending", new PrintAscendingCommand(controller, language));
        addCommand("language", new LanguageCommand(invoker));
        return commandsMap;
    }

    private void addCommand(String commandName, Command command) {
        commandsMap.put(commandName, command);
    }

}
