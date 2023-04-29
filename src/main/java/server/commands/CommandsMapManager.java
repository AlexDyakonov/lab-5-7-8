package server.commands;

import server.authentication.ROLES;
import server.commands.adminlist.ClearAllCommand;
import server.commands.adminlist.SetRoleCommand;
import server.commands.adminlist.ShowUsersCommand;
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
    private final ScriptManager scriptManager = new ScriptManager(null);
    private final Invoker invoker;
    private final HistoryManager history;
    private final HumanController controller;
    private ROLES role;
    private BufferedReader cmdReader;
    private BufferedReader fileReader;
    private BuilderType builderType;
    private final LANGUAGE language;

    public CommandsMapManager(Invoker invoker, HistoryManager history, HumanController controller, BufferedReader cmdReader, BufferedReader fileReader, BuilderType builderType, LANGUAGE language) {
        this.invoker = invoker;
        this.history = history;
        this.controller = controller;
        this.cmdReader = cmdReader;
        this.fileReader = fileReader;
        this.builderType = builderType;
        this.language = language;
    }

    public Map<String, Command> getCommandsMap() {
        if (role == null) {
            return new HashMap<>();
        }
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
        addCommand("clear_all", new ClearAllCommand(controller, language));
        addCommand("set_role", new SetRoleCommand(controller, language));
        addCommand("show_users", new ShowUsersCommand(controller, language));
        commandsMap.putAll(userMap());
        return commandsMap;
    }

    public Map<String, Command> userMap() {
        addCommand("add", new AddCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("update", new UpdateCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("remove_by_id", new RemoveByIdCommand(controller, language));
        addCommand("clear", new ClearCommand(controller, language));
        addCommand("execute_script", new ExecuteScriptCommand(invoker, scriptManager, language));
        addCommand("add_if_max", new AddIfMaxCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("add_if_min", new AddIfMinCommand(controller, cmdReader, fileReader, builderType, language));
        commandsMap.putAll(guestMap());
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

    public CommandsMapManager setCmdReader(BufferedReader cmdReader) {
        this.cmdReader = cmdReader;
        return this;
    }

    public CommandsMapManager setFileReader(BufferedReader fileReader) {
        this.fileReader = fileReader;
        return this;
    }

    public CommandsMapManager setBuilderType(BuilderType builderType) {
        this.builderType = builderType;
        return this;
    }

    private void addCommand(String commandName, Command command) {
        commandsMap.put(commandName, command);
    }

    public void setRole(ROLES role) {
        this.role = role;
    }
}
