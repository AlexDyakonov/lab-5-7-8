package server.commands.list;

import server.commands.Command;
import server.controller.HumanController;
import server.exception.ArgumentException;
import server.services.BuilderType;
import server.services.builders.HumanBeingRequestDTOBuilder;
import util.LANGUAGE;

import java.io.BufferedReader;

import static client.ui.ConsoleColors.unsuccess;
import static util.Message.getCommandDescription;
import static util.Message.getError;

public class UpdateCommand implements Command {
    private final HumanController controller;
    private final BufferedReader cmdReader;
    private final BufferedReader fileReader;
    private final BuilderType builderType;
    private LANGUAGE language;

    public UpdateCommand(HumanController controller, BufferedReader cmdReader, BufferedReader fileReader, BuilderType builderType, LANGUAGE language) {
        this.controller = controller;
        this.cmdReader = cmdReader;
        this.fileReader = fileReader;
        this.builderType = builderType;
        this.language = language;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            throw new ArgumentException(getError("one_arg", language));
        }
        Long id = Long.parseLong(args[1]);
        if (controller.getHumanById(id) == null) {
            throw new ArgumentException(unsuccess("Объект с id: " + id + " не был найден."));
        }
        controller.updateHuman(HumanBeingRequestDTOBuilder.build(cmdReader, fileReader, builderType, language), id);
    }

    @Override
    public String description() {
        return getCommandDescription("update", language);
    }
}
