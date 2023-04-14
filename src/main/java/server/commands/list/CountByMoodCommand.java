package server.commands.list;

import server.commands.Command;
import server.controller.HumanController;
import server.exception.ArgumentException;
import util.LANGUAGE;

import static util.Message.getCommandDescription;
import static util.Message.getError;

public class CountByMoodCommand implements Command {
    private final HumanController controller;

    public CountByMoodCommand(HumanController controller) {
        this.controller = controller;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            throw new ArgumentException(getError("one_arg", LANGUAGE.RU));
        }
        controller.countByMood(args[1]);
    }

    @Override
    public String description() {
        return getCommandDescription("count_by_mood", LANGUAGE.RU);
    }
}
