package server.commands.list;

import server.commands.Command;
import server.controller.HumanController;
import server.exception.ArgumentException;
import util.LANGUAGE;

import static util.Message.getCommandDescription;
import static util.Message.getError;

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
