package ru.home.app.server.commands.list;

import ru.home.app.server.commands.Command;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.util.LANGUAGE;

import static ru.home.app.util.Message.getCommandDescription;
import static ru.home.app.util.Message.getError;

/**
 * The type Clear command. Clears database.
 */
public class ClearCommand implements Command {
    private final HumanController controller;
    private final LANGUAGE language;

    /**
     * Instantiates a new Clear command.
     *
     * @param controller the controller
     * @param language   the language
     */
    public ClearCommand(HumanController controller, LANGUAGE language) {
        this.controller = controller;
        this.language = language;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            throw new ArgumentException(getError("no_args", language));
        }
        controller.clear();
    }

    @Override
    public String description() {
        return getCommandDescription("clear", language);
    }
}
