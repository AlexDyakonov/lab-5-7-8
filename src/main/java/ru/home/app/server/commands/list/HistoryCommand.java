package ru.home.app.server.commands.list;

import ru.home.app.server.commands.Command;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.server.services.HistoryManager;
import ru.home.app.util.LANGUAGE;

import static ru.home.app.util.Message.getCommandDescription;
import static ru.home.app.util.Message.getError;

/**
 * The type History command. Shows history of commands.
 */
public class HistoryCommand implements Command {
    private final HistoryManager history;
    private final LANGUAGE language;

    /**
     * Instantiates a new History command.
     *
     * @param history  the history
     * @param language the language
     */
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
