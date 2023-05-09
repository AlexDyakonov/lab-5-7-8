package ru.home.app.server.services;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static ru.home.app.server.services.LoggerManager.setupLogger;
import static ru.home.app.util.Message.getLog;

/**
 * The type History manager.
 */
public class HistoryManager {
    private static final Logger logger = Logger.getLogger(HistoryManager.class.getName());

    static {
        setupLogger(logger);
    }

    private final List<String> historyListOfCommands;
    private final int historySizeLimit;

    /**
     * Instantiates a new History manager.
     *
     * @param historySizeLimit the history size limit
     */
    public HistoryManager(int historySizeLimit) {
        this.historyListOfCommands = new LinkedList<>();
        this.historySizeLimit = historySizeLimit;
        logger.info(getLog("hm_init_finish"));
    }

    /**
     * Add command to history.
     *
     * @param command the command
     */
    public void addCommandToHistory(String command) {
        if (historyListOfCommands.size() < historySizeLimit) {
            historyListOfCommands.add(command);
        } else {
            historyListOfCommands.remove(0);
            historyListOfCommands.add(command);
        }
        logger.info(getLog("command_to_hm"));
    }

    /**
     * Gets history list of commands.
     *
     * @return the history list of commands
     */
    public List<String> getHistoryListOfCommands() {
        return historyListOfCommands;
    }

}
