package ru.home.app.server.commands.list;

import ru.home.app.server.commands.Command;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.util.LANGUAGE;

import static ru.home.app.util.Message.getCommandDescription;
import static ru.home.app.util.Message.getError;

/**
 * The type Exit command. Ends program.
 */
public class ExitCommand implements Command {
    private final LANGUAGE language;

    /**
     * Instantiates a new Exit command.
     *
     * @param language the language
     */
    public ExitCommand(LANGUAGE language) {
        this.language = language;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            throw new ArgumentException(getError("no_args", language));
        }
    }

    @Override
    public String description() {
        return getCommandDescription("exit", language);
    }
}
