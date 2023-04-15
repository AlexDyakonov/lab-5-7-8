package server.commands.list;

import server.commands.Command;
import server.commands.Invoker;
import server.exception.ArgumentException;
import server.services.builders.LanguageBuilder;
import util.LANGUAGE;

import static util.Message.getCommandDescription;
import static util.Message.getError;

public class LanguageCommand implements Command {
    private final Invoker invoker;

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
