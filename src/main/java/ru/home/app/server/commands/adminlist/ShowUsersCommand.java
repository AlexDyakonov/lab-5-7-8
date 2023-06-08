package ru.home.app.server.commands.adminlist;

import ru.home.app.server.commands.Command;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.util.language.LANGUAGE;

import static ru.home.app.util.Message.getCommandDescription;
import static ru.home.app.util.Message.getError;

/**
 * The type Show users command. Show all users, only admins
 */
public class ShowUsersCommand implements Command {
    private final HumanController controller;
    private final LANGUAGE language;

    /**
     * Instantiates a new Show users command.
     *
     * @param controller the controller
     * @param language   the language
     */
    public ShowUsersCommand(HumanController controller, LANGUAGE language) {
        this.controller = controller;
        this.language = language;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            throw new ArgumentException(getError("no_args", language));
        }
        controller.getAllUsers().forEach(System.out::println);
    }

    @Override
    public String description() {
        return getCommandDescription("show_users", language);
    }
}
