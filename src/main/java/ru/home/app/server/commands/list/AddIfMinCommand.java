package ru.home.app.server.commands.list;

import ru.home.app.server.commands.Command;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.server.model.dto.HumanBeingRequestDTO;
import ru.home.app.server.services.builders.BuilderType;
import ru.home.app.server.services.builders.HumanBeingRequestDTOBuilder;
import ru.home.app.util.LANGUAGE;

import java.io.BufferedReader;

import static ru.home.app.util.Message.getCommandDescription;
import static ru.home.app.util.Message.getError;

/**
 * The type Add if min command. Adds HumanBeing to db if impact speed is min.
 */
public class AddIfMinCommand implements Command {
    private final HumanController controller;
    private final BufferedReader cmdReader;
    private final BufferedReader fileReader;
    private final BuilderType builderType;
    private final LANGUAGE language;

    /**
     * Instantiates a new Add if min command.
     *
     * @param controller  the controller
     * @param cmdReader   the cmd reader
     * @param fileReader  the file reader
     * @param builderType the builder type
     * @param language    the language
     */
    public AddIfMinCommand(HumanController controller, BufferedReader cmdReader, BufferedReader fileReader, BuilderType builderType, LANGUAGE language) {
        this.language = language;
        this.controller = controller;
        this.cmdReader = cmdReader;
        this.fileReader = fileReader;
        this.builderType = builderType;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            throw new ArgumentException(getError("no_args", language));
        }
        HumanBeingRequestDTO dtoMin = HumanBeingRequestDTOBuilder.build(cmdReader, fileReader, builderType, language);
        if (controller.isImpactSpeedMin(dtoMin)) {
            controller.createHuman(dtoMin);
        }
    }

    @Override
    public String description() {
        return getCommandDescription("add_if_min", language);
    }
}
