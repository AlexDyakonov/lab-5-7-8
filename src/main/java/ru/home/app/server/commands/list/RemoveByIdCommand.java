package ru.home.app.server.commands.list;

import ru.home.app.server.commands.Command;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.util.LANGUAGE;

import static ru.home.app.util.Message.getCommandDescription;
import static ru.home.app.util.Message.getError;

/**
 * The type Remove by id command. Removes HumanBeing from database
 */
public class RemoveByIdCommand implements Command {
    private final HumanController controller;
    private final LANGUAGE language;

    /**
     * Instantiates a new Remove by id command.
     *
     * @param controller the controller
     * @param language   the language
     */
    public RemoveByIdCommand(HumanController controller, LANGUAGE language) {
        this.controller = controller;
        this.language = language;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            throw new ArgumentException(getError("one_arg", language));
        }
        Long id = Long.parseLong(args[1]);
        controller.deleteHumanById(id);
    }

    @Override
    public String description() {
        return getCommandDescription("remove_by_id", language);
    }
}
