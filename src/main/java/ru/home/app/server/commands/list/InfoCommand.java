package ru.home.app.server.commands.list;

import ru.home.app.server.commands.Command;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.util.LANGUAGE;

import java.util.logging.Logger;

import static ru.home.app.server.services.LoggerManager.setupLogger;
import static ru.home.app.util.Message.*;

/**
 * The type Info command. Shows some info about data base.
 */
public class InfoCommand implements Command {
    private static final Logger logger = Logger.getLogger(InfoCommand.class.getName());
    private final HumanController controller;
    private final LANGUAGE language;

    /**
     * Instantiates a new Info command.
     *
     * @param controller the controller
     * @param language   the language
     */
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
