package ru.home.app.server.commands.adminlist;

import ru.home.app.server.authentication.ROLES;
import ru.home.app.server.commands.Command;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.util.language.LANGUAGE;

import static ru.home.app.util.Message.getCommandDescription;
import static ru.home.app.util.Message.getError;
import static ru.home.app.util.Parser.stringToRole;

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
