package server.commands.adminlist;

import server.commands.Command;
import server.controller.HumanController;
import server.exception.ArgumentException;
import util.LANGUAGE;

import static util.Message.getCommandDescription;
import static util.Message.getError;

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
