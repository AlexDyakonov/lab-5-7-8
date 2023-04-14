package server.commands.list;

import server.commands.Command;
import server.controller.HumanController;
import server.exception.ArgumentException;
import util.LANGUAGE;

import static util.Message.getCommandDescription;
import static util.Message.getError;

public class MaxByImpactSpeedCommand implements Command {
    private final HumanController controller;

    public MaxByImpactSpeedCommand(HumanController controller) {
        this.controller = controller;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            throw new ArgumentException(getError("no_args", LANGUAGE.RU));
        }
        controller.max_by_impact_speed();
    }

    @Override
    public String description() {
        return getCommandDescription("max_by_impact_speed", LANGUAGE.RU);
    }
}
