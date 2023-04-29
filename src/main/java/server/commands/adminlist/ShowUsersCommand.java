package server.commands.adminlist;

import server.commands.Command;
import server.controller.HumanController;
import server.exception.ArgumentException;
import util.LANGUAGE;

import static util.Message.getCommandDescription;
import static util.Message.getError;

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
