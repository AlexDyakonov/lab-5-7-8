package server.commands.list;

import server.commands.Command;
import server.commands.Invoker;
import server.exception.ArgumentException;
import util.LANGUAGE;

import java.util.Map;

import static client.ui.ConsoleColors.GREEN;
import static client.ui.ConsoleColors.RESET;
import static util.Message.getCommandDescription;
import static util.Message.getError;

/**
 * The type Help command. Shows all commands.
 */
public class HelpCommand implements Command {
    private final LANGUAGE language;

    /**
     * Instantiates a new Help command.
     *
     * @param language the language
     */
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
                System.out.println(GREEN + pair.getKey() + RESET + " : " + pair.getValue().description());
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
