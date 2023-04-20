package server.commands.list;

import server.commands.Command;
import server.controller.HumanController;
import server.dao.HumanDaoImpl;
import server.exception.ArgumentException;
import util.LANGUAGE;

import java.util.logging.Logger;

import static server.services.LoggerManager.setupLogger;
import static util.Message.*;

public class ShowCommand implements Command {
    private static final Logger logger = Logger.getLogger(ShowCommand.class.getName());
    private final HumanController controller;
    private LANGUAGE language;

    public ShowCommand(HumanController controller, LANGUAGE language) {
        this.controller = controller;
        this.language = language;
        setupLogger(logger);
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            throw new ArgumentException(getError("no_args", language));
        }
        controller.getAllHuman().forEach(System.out::println);
        logger.info(getLog("elements_showed").replace("%num%", String.valueOf(controller.getAllHuman().size())));
    }

    @Override
    public String description() {
        return getCommandDescription("show", language);
    }
}
