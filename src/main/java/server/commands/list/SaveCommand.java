package server.commands.list;

import server.commands.Command;
import server.controller.HumanController;
import server.exception.ArgumentException;
import util.LANGUAGE;

import static util.Message.getCommandDescription;
import static util.Message.getError;

/**
 * The type Save command. Saves database to csv file.
 */
@Deprecated
public class SaveCommand implements Command {
    private final HumanController controller;
    private final String fileName;
    private final LANGUAGE language;

    /**
     * Instantiates a new Save command.
     *
     * @param controller the controller
     * @param fileName   the file name
     * @param language   the language
     */
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
        controller.save(fileName, language);
    }

    @Override
    public String description() {
        return getCommandDescription("save", language);
    }
}
