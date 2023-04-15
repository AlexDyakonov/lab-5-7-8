package server.commands.list;

import server.commands.Command;
import server.controller.HumanController;
import server.exception.ArgumentException;
import util.LANGUAGE;

import static util.Message.getCommandDescription;
import static util.Message.getError;

public class RemoveByIdCommand implements Command {
    private final HumanController controller;
    private LANGUAGE language;

    public RemoveByIdCommand(HumanController controller, LANGUAGE language) {
        this.controller = controller;
        this.language = language;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            throw new ArgumentException(getError("one_args", language));
        }
        Long id = Long.parseLong(args[1]);
        controller.deleteHumanById(id);
    }

    @Override
    public String description() {
        return getCommandDescription("remove_by_id", language);
    }
}
