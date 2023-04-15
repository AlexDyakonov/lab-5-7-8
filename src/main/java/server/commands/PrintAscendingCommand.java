package server.commands;

import server.controller.HumanController;
import util.LANGUAGE;

import static util.Message.getCommandDescription;

public class PrintAscendingCommand implements Command {
    private final HumanController controller;
    private LANGUAGE language;

    public PrintAscendingCommand(HumanController controller, LANGUAGE language) {
        this.controller = controller;
        this.language = language;
    }

    @Override
    public void execute(String[] args) {
        controller.print_ascending().forEach(System.out::println);
        System.out.println("Было успшно выведено " + controller.getAllHuman().size() + "элементов");
    }

    @Override
    public String description() {
        return getCommandDescription("print_ascending", language);
    }
}
