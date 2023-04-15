package server.commands.list;

import server.commands.Command;
import server.controller.HumanController;
import server.exception.ArgumentException;
import util.LANGUAGE;

import static util.Message.getCommandDescription;
import static util.Message.getError;

public class ShowCommand implements Command {
    private final HumanController controller;
    private LANGUAGE language;

    public ShowCommand(HumanController controller, LANGUAGE language) {
        this.controller = controller;
        this.language = language;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            throw new ArgumentException(getError("no_args", language));
        }
        controller.getAllHuman().forEach(System.out::println);
    }

    @Override
    public String description() {
        return getCommandDescription("show", language);
    }
}
