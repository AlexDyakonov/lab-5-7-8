package server.commands.adminlist;

import server.authentication.ROLES;
import server.commands.Command;
import server.controller.HumanController;
import server.exception.ArgumentException;
import util.LANGUAGE;

import static util.Message.getCommandDescription;
import static util.Message.getError;
import static util.Parser.stringToRole;

/**
 * The type Set role command. Set role to user, only admins
 */
public class SetRoleCommand implements Command {
    private final HumanController controller;
    private final LANGUAGE language;

    /**
     * Instantiates a new Set role command.
     *
     * @param controller the controller
     * @param language   the language
     */
    public SetRoleCommand(HumanController controller, LANGUAGE language) {
        this.controller = controller;
        this.language = language;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            throw new ArgumentException(getError("one_arg", language));
        }
        String username = args[1];
        ROLES roles = stringToRole(args[2]);
        controller.setRole(username, roles);
    }

    @Override
    public String description() {
        return getCommandDescription("set_role", language);
    }
}
