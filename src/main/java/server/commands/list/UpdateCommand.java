package server.commands.list;

import server.commands.Command;
import server.controller.HumanController;
import server.exception.ArgumentException;
import server.exception.ValidationException;
import server.services.BuilderType;
import server.services.builders.HumanBeingRequestDTOBuilder;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.util.Objects;

import static client.ui.ConsoleColors.unsuccess;
import static util.Message.*;

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
        try {
            Long id = Long.parseLong(args[1]);
            if (id <= 0) {
                throw new ValidationException(getError("id_more_than_zero", language));
            }
            if (controller.getHumanById(id) == null) {
                throw new ValidationException((getWarning("user_not_found", language)).replace("%id%", id.toString()));
            }
            controller.updateHuman(HumanBeingRequestDTOBuilder.build(cmdReader, fileReader, builderType, language), id);
        } catch (NumberFormatException e) {
            throw new ArgumentException(getError("number_error", language));
        }
    }

    @Override
    public String description() {
        return getCommandDescription("update", language);
    }
}
