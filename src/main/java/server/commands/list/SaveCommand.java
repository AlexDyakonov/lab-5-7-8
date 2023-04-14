package server.commands.list;

import server.commands.Command;
import server.controller.HumanController;
import server.exception.ArgumentException;
import util.LANGUAGE;

import static util.Message.getCommandDescription;
import static util.Message.getError;

public class SaveCommand implements Command {
    private final HumanController controller;
    private final String fileName;

    public SaveCommand(HumanController controller, String fileName) {
        this.controller = controller;
        this.fileName = fileName;
    }


    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            throw new ArgumentException(getError("no_args", LANGUAGE.RU));
        }
        controller.save(fileName);
    }

    @Override
    public String description() {
        return getCommandDescription("save", LANGUAGE.RU);
    }
}
