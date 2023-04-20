package server.commands.list;

import server.commands.Command;
import server.controller.HumanController;
import util.LANGUAGE;

import static util.Message.getCommandDescription;
import static util.Message.getSuccessMessage;

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
