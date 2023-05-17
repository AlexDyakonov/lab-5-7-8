package ru.home.app.server.commands;

import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.controller.HumanControllerImpl;
import ru.home.app.server.exception.ApplicationException;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.server.exception.FileException;
import ru.home.app.server.exception.ValidationException;
import ru.home.app.server.services.builders.BuilderType;
import ru.home.app.server.services.HistoryManager;
import ru.home.app.server.services.ScriptManager;
import ru.home.app.util.LANGUAGE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static ru.home.app.server.services.LoggerManager.setupLogger;
import static ru.home.app.util.Message.getLog;
import static ru.home.app.util.Message.getWarning;


/**
 * The type Invoker. Execute commands.
 */
public class Invoker {
    /**
     * The constant logger.
     */
    public static final Logger logger = Logger.getLogger(Invoker.class.getName());
    private static Map<String, Command> commandsMap = new HashMap<>();
    private final ScriptManager scriptManager = new ScriptManager(null);
    private final HistoryManager history;
    private final HumanController controller;
    private CurrentUserManager currentUserManager = new CurrentUserManager();
    private BufferedReader cmdReader;
    private BufferedReader fileReader;
    private BuilderType builderType;
    private LANGUAGE language;
    private final CommandsMapManager commandsMapManager;

    static {
        setupLogger(logger);
    }

    /**
     * Instantiates a new Invoker.
     *
     * @param builderType the builder type
     * @param language    the language
     */
    public Invoker(BuilderType builderType, LANGUAGE language) {
        logger.info(getLog("invoker_init_start"));
        this.controller = new HumanControllerImpl();
        this.history = new HistoryManager(15); // limit history size
        this.cmdReader = cmdReader == null ? new BufferedReader(new InputStreamReader(System.in)) : cmdReader;
        this.builderType = builderType;
        this.language = language;
        controller.setLanguage(language);
        commandsMapManager = new CommandsMapManager(this, history, controller, cmdReader, fileReader, builderType, language);
        init();
        logger.info(getLog("invoker_init_finish"));
    }

    /**
     * Gets commands map.
     *
     * @return the commands map
     */
    public static Map<String, Command> getCommandsMap() {
        return commandsMap;
    }

    /**
     * Init.
     */
    public void init() {
        logger.info(getLog("command_init_start"));
        commandsMapManager.setBuilderType(builderType).setCmdReader(cmdReader).setFileReader(fileReader);
        commandsMap = commandsMapManager.getCommandsMap();
        logger.info(getLog("command_init_finish"));
    }

    /**
     * Execute.
     *
     * @param input the input
     */
    public void execute(String input) {
        try {
            String[] commandArray = input.split(" ");
            String command = commandArray[0];
            for (Map.Entry<String, Command> pair : Invoker.getCommandsMap().entrySet()) {
                if (pair.getKey().equals(commandArray[0])) {
                    logger.info(getLog("command_executing").replace("%command%", command));
                    pair.getValue().execute(commandArray);
                    history.addCommandToHistory(command);
                    logger.info(getLog("command_executed").replace("%command%", command));
                }
            }
            if (!Invoker.getCommandsMap().containsKey(commandArray[0])) {
                System.out.println(getWarning("command_not_found", language));
                logger.warning(getLog("command_not_found"));
            }
        } catch (ApplicationException | FileException | ValidationException e) {
            System.out.println(e.getMessage());
            logger.severe(e.getMessage());
        } catch (ArgumentException e) {
            System.out.println(e.getMessage());
            logger.warning(getLog("no_args"));
        }
    }

    private void addCommand(String commandName, Command command) {
        commandsMap.put(commandName, command);
    }

    /**
     * Gets cmd reader.
     *
     * @return the cmd reader
     */
    public BufferedReader getCmdReader() {
        return cmdReader;
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    public LANGUAGE getLanguage() {
        return language;
    }

    /**
     * Sets language.
     *
     * @param language the language
     */
    public void setLanguage(LANGUAGE language) {
        this.language = language;
        controller.setLanguage(language);
        init();
    }

    public HumanController getController() {
        return controller;
    }

    public CurrentUserManager getUserManager() {
        return currentUserManager;
    }

    public void setUserManager(CurrentUserManager currentUserManager) {
        this.currentUserManager = currentUserManager;
        commandsMapManager.setRole(currentUserManager.getUserRole());
        init();
    }

    public ScriptManager getScriptManager() {
        return scriptManager;
    }

    public HistoryManager getHistory() {
        return history;
    }

    public BufferedReader getFileReader() {
        return fileReader;
    }

    /**
     * Sets file reader.
     *
     * @param fileReader the file reader
     * @return the file reader
     */
    public Invoker setFileReader(BufferedReader fileReader) {
        this.fileReader = fileReader;
        return this;
    }

    public BuilderType getBuilderType() {
        return builderType;
    }

    /**
     * Sets builder type.
     *
     * @param builderType the builder type
     * @return the builder type
     */
    public Invoker setBuilderType(BuilderType builderType) {
        this.builderType = builderType;
        return this;
    }
}
