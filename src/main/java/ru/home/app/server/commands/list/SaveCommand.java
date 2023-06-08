package ru.home.app.server.commands.list;

import ru.home.app.server.commands.Command;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.util.language.LANGUAGE;

import static ru.home.app.util.Message.getCommandDescription;
import static ru.home.app.util.Message.getError;

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
