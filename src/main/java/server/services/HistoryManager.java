package server.services;

import java.util.LinkedList;
import java.util.List;

public class HistoryManager {
    private List<String> historyListOfCommands;
    private int historySizeLimit;

    public HistoryManager(int historySizeLimit) {
        this.historyListOfCommands = new LinkedList<>();
        this.historySizeLimit = historySizeLimit;
    }

    public void addCommandToHistory(String command) {
        if (historyListOfCommands.size() < historySizeLimit) {
            historyListOfCommands.add(command);
        } else {
            historyListOfCommands.remove(0);
            historyListOfCommands.add(command);
        }
    }

    public List<String> getHistoryListOfCommands() {
        return historyListOfCommands;
    }

    public void clearHistoryListOfCommands() {
        historyListOfCommands.clear();
    }
}
