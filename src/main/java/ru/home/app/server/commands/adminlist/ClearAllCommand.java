package ru.home.app.server.commands.adminlist;

import ru.home.app.server.commands.Command;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.util.LANGUAGE;

import static ru.home.app.util.Message.getCommandDescription;
import static ru.home.app.util.Message.getError;

/**
 * The type Clear all command. Clears whole db, only admins
 */
public class ClearAllCommand implements Command {
    private final HumanController controller;
    private final LANGUAGE language;

    /**
     * Instantiates a new Clear all command.
     *
     * @param controller the controller
     * @param language   the language
     */
    public ClearAllCommand(HumanController controller, LANGUAGE language) {
        this.controller = controller;
        this.language = language;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            throw new ArgumentException(getError("no_args", language));
        }
        controller.clearAll();
    }

    @Override
    public String description() {
        return getCommandDescription("clear_all", language);
    }
}
