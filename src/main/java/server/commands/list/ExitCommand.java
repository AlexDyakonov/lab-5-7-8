package server.commands.list;

import server.commands.Command;
import server.exception.ArgumentException;
import util.LANGUAGE;

import static util.Message.getCommandDescription;
import static util.Message.getError;

public class ExitCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            throw new ArgumentException(getError("no_args", LANGUAGE.RU));
        }
        System.exit(0);
    }

    @Override
    public String description() {
        return getCommandDescription("exit", LANGUAGE.RU);
    }
}
