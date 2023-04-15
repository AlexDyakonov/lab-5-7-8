package server.commands.list;

import server.commands.Command;
import server.commands.Invoker;
import server.exception.ArgumentException;
import util.LANGUAGE;

import java.util.Map;

import static util.Message.getCommandDescription;
import static util.Message.getError;

public class HelpCommand implements Command {
    private LANGUAGE language;

    public HelpCommand(LANGUAGE language) {
        this.language = language;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            throw new ArgumentException(getError("no_args", language));
        }
        try {
            for (Map.Entry<String, Command> pair : Invoker.getCommandsMap().entrySet()) {
                System.out.println(pair.getKey() + " : " + pair.getValue().description());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String description() {
        return getCommandDescription("help", language);
    }
}
