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
    private LANGUAGE language;

    public SaveCommand(HumanController controller, String fileName, LANGUAGE language) {
        this.controller = controller;
        this.fileName = fileName;
        this.language = language;
    }


    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            throw new ArgumentException(getError("no_args", language));
        }
        controller.save(fileName);
    }

    @Override
    public String description() {
        return getCommandDescription("save", language);
    }
}
