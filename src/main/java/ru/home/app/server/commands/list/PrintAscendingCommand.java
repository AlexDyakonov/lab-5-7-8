package ru.home.app.server.commands.list;

import ru.home.app.server.commands.Command;
import ru.home.app.server.controller.HumanController;
import ru.home.app.util.LANGUAGE;

import static ru.home.app.util.Message.getCommandDescription;
import static ru.home.app.util.Message.getSuccessMessage;

/**
 * The type Print ascending command. Print HumanBeings in ascending
 */
public class PrintAscendingCommand implements Command {
    private final HumanController controller;
    private final LANGUAGE language;

    /**
     * Instantiates a new Print ascending command.
     *
     * @param controller the controller
     * @param language   the language
     */
    public PrintAscendingCommand(HumanController controller, LANGUAGE language) {
        this.controller = controller;
        this.language = language;
    }

    @Override
    public void execute(String[] args) {
        controller.print_ascending().forEach(System.out::println);
        System.out.println((getSuccessMessage("output_db", language)).replace("%num%", String.valueOf(controller.getAllHuman().size())));
    }

    @Override
    public String description() {
        return getCommandDescription("print_ascending", language);
    }
}
