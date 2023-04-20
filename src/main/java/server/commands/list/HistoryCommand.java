package server.commands.list;

import server.commands.Command;
import server.exception.ArgumentException;
import server.services.HistoryManager;
import util.LANGUAGE;

import static util.Message.getCommandDescription;
import static util.Message.getError;

public class HistoryCommand implements Command {
    private final HistoryManager history;
    private final LANGUAGE language;

    public HistoryCommand(HistoryManager history, LANGUAGE language) {
        this.history = history;
        this.language = language;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            throw new ArgumentException(getError("no_args", language));
        }
        history.getHistoryListOfCommands().forEach(System.out::println);
    }

    @Override
    public String description() {
        return getCommandDescription("history", language);
    }
}
