package ru.home.app.server.commands.list;

import ru.home.app.server.commands.Command;
import ru.home.app.server.commands.Invoker;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.util.LANGUAGE;

import java.util.Map;

import static ru.home.app.ui.ConsoleColors.GREEN;
import static ru.home.app.ui.ConsoleColors.RESET;
import static ru.home.app.util.Message.getCommandDescription;
import static ru.home.app.util.Message.getError;

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
