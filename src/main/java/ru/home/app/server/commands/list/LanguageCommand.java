package ru.home.app.server.commands.list;

import ru.home.app.server.commands.Command;
import ru.home.app.server.commands.Invoker;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.server.services.builders.LanguageBuilder;

import static ru.home.app.util.Message.getCommandDescription;
import static ru.home.app.util.Message.getError;

/**
 * The type Language command. Change language of system.
 */
public class LanguageCommand implements Command {
    private final Invoker invoker;

    /**
     * Instantiates a new Language command.
     *
     * @param invoker the invoker
     */
    public LanguageCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            throw new ArgumentException(getError("no_args", invoker.getLanguage()));
        }
        try {
            invoker.setLanguage(LanguageBuilder.getLanguage(invoker.getLanguage(), invoker.getCmdReader()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String description() {
        return getCommandDescription("language", invoker.getLanguage());
    }
}
