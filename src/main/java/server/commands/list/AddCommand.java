package server.commands.list;

import server.commands.Command;
import server.controller.HumanController;
import server.exception.ArgumentException;
import server.services.BuilderType;
import server.services.builders.HumanBeingRequestDTOBuilder;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.FileReader;

import static util.Message.getCommandDescription;
import static util.Message.getError;

public class AddCommand implements Command {
    private final HumanController controller;
    private final BufferedReader cmdReader;
    private final BufferedReader fileReader;
    private final BuilderType builderType;

    public AddCommand(HumanController controller, BufferedReader cmdReader, BufferedReader fileReader, BuilderType builderType) {
        this.controller = controller;
        this.cmdReader = cmdReader;
        this.fileReader = fileReader;
        this.builderType = builderType;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            throw new ArgumentException(getError("no_args", LANGUAGE.RU));
        }
        controller.createHuman(HumanBeingRequestDTOBuilder.build(cmdReader, fileReader, builderType));
    }

    @Override
    public String description() {
        return getCommandDescription("add", LANGUAGE.RU);
    }
}
