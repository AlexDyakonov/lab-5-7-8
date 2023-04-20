package server.commands.list;

import server.commands.Command;
import server.controller.HumanController;
import server.exception.ArgumentException;
import util.LANGUAGE;

import static util.Message.getCommandDescription;
import static util.Message.getError;

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
        controller.countByMood(args[1]);
    }

    @Override
    public String description() {
        return getCommandDescription("count_by_mood", language);
    }
}
