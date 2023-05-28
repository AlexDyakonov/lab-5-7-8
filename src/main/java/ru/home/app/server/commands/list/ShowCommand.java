package ru.home.app.server.commands.list;

import ru.home.app.server.commands.Command;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.util.language.LANGUAGE;

import java.util.logging.Logger;

import static ru.home.app.server.services.LoggerManager.setupLogger;
import static ru.home.app.util.Message.*;

/**
 * The type Show command. Show all elements in database
 */
public class ShowCommand implements Command {
    private static final Logger logger = Logger.getLogger(ShowCommand.class.getName());
    private final HumanController controller;
    private final LANGUAGE language;

    /**
     * Instantiates a new Show command.
     *
     * @param controller the controller
     * @param language   the language
     */
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
