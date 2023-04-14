package server.commands.list;

import server.commands.Command;
import server.controller.HumanController;
import server.exception.ArgumentException;
import util.LANGUAGE;

import static util.Message.getCommandDescription;
import static util.Message.getError;

public class InfoCommand implements Command {
    private final HumanController controller;

    public InfoCommand(HumanController controller) {
        this.controller = controller;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            throw new ArgumentException(getError("no_args", LANGUAGE.RU));
        }
        System.out.println(controller.info());
    }

    @Override
    public String description() {
        return getCommandDescription("info", LANGUAGE.RU);
    }
}
