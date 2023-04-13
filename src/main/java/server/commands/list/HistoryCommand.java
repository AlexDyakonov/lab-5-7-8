package server.commands.list;

import server.commands.Command;
import server.exception.ArgumentException;
import server.services.HistoryManager;
import util.LANGUAGE;

import static util.Message.*;

public class HistoryCommand implements Command {
    private HistoryManager history;

    public HistoryCommand(HistoryManager history) {
        this.history = history;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            throw new ArgumentException(getError("no_args", LANGUAGE.RU));
        }
        history.getHistoryListOfCommands().forEach(System.out::println);
    }

    @Override
    public String description() {
        return getCommandDescription("history", LANGUAGE.RU);
    }
}
