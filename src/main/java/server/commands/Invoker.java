package server.commands;

import server.commands.list.*;
import server.controller.HumanController;
import server.controller.HumanControllerImpl;
import server.exception.ApplicationException;
import server.exception.ArgumentException;
import server.exception.FileException;
import server.exception.ValidationException;
import server.services.BuilderType;
import server.services.HistoryManager;
import server.services.ScriptManager;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static server.services.LoggerManager.setupLogger;
import static util.Message.getLog;
import static util.Message.getWarning;


/**
 * The type Invoker. Execute commands.
 */
public class Invoker {
    /**
     * The constant logger.
     */
    public static final Logger logger = Logger.getLogger(Invoker.class.getName());
    private static final Map<String, Command> commandsMap = new HashMap<>();
    private final ScriptManager scriptManager = new ScriptManager(null);
    private final HistoryManager history;
    private final String fileName;
    private final HumanController controller;
    private BufferedReader cmdReader;
    private BufferedReader fileReader;
    private BuilderType builderType;
    private LANGUAGE language;

    /**
     * Instantiates a new Invoker.
     *
     * @param fileName    the file name
     * @param builderType the builder type
     * @param language    the language
     */
    public Invoker(String fileName, BuilderType builderType, LANGUAGE language) {
        setupLogger(logger);
        logger.info(getLog("invoker_init_start"));
        this.fileName = fileName;
        this.controller = new HumanControllerImpl(fileName);
        this.history = new HistoryManager(15); // limit history size
        this.cmdReader = cmdReader == null ? new BufferedReader(new InputStreamReader(System.in)) : cmdReader;
        this.builderType = builderType;
        this.language = language;
        controller.setLanguage(language);
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
     * Sets file reader.
     *
     * @param fileReader the file reader
     * @return the file reader
     */
    public Invoker setFileReader(BufferedReader fileReader) {
        this.fileReader = fileReader;
        return this;
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

}
