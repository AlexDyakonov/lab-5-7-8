package ru.home.app.server.commands;

import ru.home.app.server.authentication.ROLES;
import ru.home.app.server.commands.adminlist.ClearAllCommand;
import ru.home.app.server.commands.adminlist.SetRoleCommand;
import ru.home.app.server.commands.adminlist.ShowUsersCommand;
import ru.home.app.server.commands.list.*;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.services.builders.BuilderType;
import ru.home.app.server.services.HistoryManager;
import ru.home.app.server.services.ScriptManager;
import ru.home.app.util.LANGUAGE;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static ru.home.app.server.services.LoggerManager.setupLogger;
import static ru.home.app.util.Message.getLog;

/**
 * The type Commands map manager. Class to choose commandsMap in case of user role
 */
public class CommandsMapManager {
    public static final Logger logger = Logger.getLogger(CommandsMapManager.class.getName());
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

    static {
        setupLogger(logger);
    }

    /**
     * Instantiates a new Commands map manager.
     *
     * @param invoker     the invoker
     * @param history     the history
     * @param controller  the controller
     * @param cmdReader   the cmd reader
     * @param fileReader  the file reader
     * @param builderType the builder type
     * @param language    the language
     */
    public CommandsMapManager(Invoker invoker, HistoryManager history, HumanController controller, BufferedReader cmdReader, BufferedReader fileReader, BuilderType builderType, LANGUAGE language) {
        this.invoker = invoker;
        this.history = history;
        this.controller = controller;
        this.cmdReader = cmdReader;
        this.fileReader = fileReader;
        this.builderType = builderType;
        this.language = language;
    }

    /**
     * Gets commands map.
     *
     * @return the commands map
     */
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


    /**
     * Admin map map.
     *
     * @return the map
     */
    public Map<String, Command> adminMap() {
        addCommand("clear_all", new ClearAllCommand(controller, language));
        addCommand("set_role", new SetRoleCommand(controller, language));
        addCommand("show_users", new ShowUsersCommand(controller, language));
        commandsMap.putAll(userMap());
        logger.info(getLog("admin_map_init"));
        return commandsMap;
    }

    /**
     * User map map.
     *
     * @return the map
     */
    public Map<String, Command> userMap() {
        addCommand("add", new AddCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("update", new UpdateCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("remove_by_id", new RemoveByIdCommand(controller, language));
        addCommand("clear", new ClearCommand(controller, language));
        addCommand("execute_script", new ExecuteScriptCommand(invoker, scriptManager, language));
        addCommand("add_if_max", new AddIfMaxCommand(controller, cmdReader, fileReader, builderType, language));
        addCommand("add_if_min", new AddIfMinCommand(controller, cmdReader, fileReader, builderType, language));
        commandsMap.putAll(guestMap());
        logger.info(getLog("user_map_init"));
        return commandsMap;
    }

    /**
     * Guest map map.
     *
     * @return the map
     */
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
        logger.info(getLog("guest_map_init"));
        return commandsMap;
    }

    /**
     * Sets cmd reader.
     *
     * @param cmdReader the cmd reader
     * @return the cmd reader
     */
    public CommandsMapManager setCmdReader(BufferedReader cmdReader) {
        this.cmdReader = cmdReader;
        return this;
    }

    /**
     * Sets file reader.
     *
     * @param fileReader the file reader
     * @return the file reader
     */
    public CommandsMapManager setFileReader(BufferedReader fileReader) {
        this.fileReader = fileReader;
        return this;
    }

    /**
     * Sets builder type.
     *
     * @param builderType the builder type
     * @return the builder type
     */
    public CommandsMapManager setBuilderType(BuilderType builderType) {
        this.builderType = builderType;
        return this;
    }

    private void addCommand(String commandName, Command command) {
        commandsMap.put(commandName, command);
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(ROLES role) {
        this.role = role;
    }
}
