package server.services;

import java.util.LinkedList;
import java.util.List;

/**
 * The type History manager.
 */
public class HistoryManager {
    private List<String> historyListOfCommands;
    private final int historySizeLimit;

    /**
     * Instantiates a new History manager.
     *
     * @param historySizeLimit the history size limit
     */
    public HistoryManager(int historySizeLimit) {
        this.historyListOfCommands = new LinkedList<>();
        this.historySizeLimit = historySizeLimit;
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
    }

    /**
     * Gets history list of commands.
     *
     * @return the history list of commands
     */
    public List<String> getHistoryListOfCommands() {
        return historyListOfCommands;
    }

    /**
     * Clear history list of commands.
     */
    public void clearHistoryListOfCommands() {
        historyListOfCommands.clear();
    }
}
