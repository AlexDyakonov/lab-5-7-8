package server.commands.list;

import server.commands.Command;
import server.controller.HumanController;
import server.exception.ArgumentException;
import util.LANGUAGE;

import java.util.logging.Logger;

import static server.services.LoggerManager.setupLogger;
import static util.Message.*;

public class InfoCommand implements Command {
    private static final Logger logger = Logger.getLogger(InfoCommand.class.getName());
    private final HumanController controller;
    private final LANGUAGE language;

    public InfoCommand(HumanController controller, LANGUAGE language) {
        this.controller = controller;
        this.language = language;
        setupLogger(logger);
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            throw new ArgumentException(getError("no_args", language));
        }
        System.out.println(controller.info());
        logger.info(getLog("info_shown"));
    }

    @Override
    public String description() {
        return getCommandDescription("info", language);
    }
}
