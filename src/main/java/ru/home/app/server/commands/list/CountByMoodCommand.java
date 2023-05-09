package ru.home.app.server.commands.list;

import ru.home.app.server.commands.Command;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.util.LANGUAGE;

import static ru.home.app.util.Message.*;

/**
 * The type Count by mood command. Counts number of HumanBeing with selected Mood.
 */
public class CountByMoodCommand implements Command {
    private final HumanController controller;
    private final LANGUAGE language;

    /**
     * Instantiates a new Count by mood command.
     *
     * @param controller the controller
     * @param language   the language
     */
    public CountByMoodCommand(HumanController controller, LANGUAGE language) {
        this.controller = controller;
        this.language = language;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            throw new ArgumentException(getError("one_arg", language));
        }
        System.out.println(getMessage("count_by_mood", language).replace("%mood%", args[1])
                .replace("%num%", String.valueOf(controller.countByMood(args[1]))));
    }

    @Override
    public String description() {
        return getCommandDescription("count_by_mood", language);
    }
}
