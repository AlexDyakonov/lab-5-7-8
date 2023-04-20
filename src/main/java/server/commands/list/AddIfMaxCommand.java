package server.commands.list;

import server.commands.Command;
import server.controller.HumanController;
import server.exception.ArgumentException;
import server.model.dto.HumanBeingRequestDTO;
import server.services.BuilderType;
import server.services.builders.HumanBeingRequestDTOBuilder;
import util.LANGUAGE;

import java.io.BufferedReader;

import static util.Message.getCommandDescription;
import static util.Message.getError;

/**
 * The type Add if max command. Adds HumanBeing to db if impact speed is max.
 */
public class AddIfMaxCommand implements Command {
    private final HumanController controller;
    private final BufferedReader cmdReader;
    private final BufferedReader fileReader;
    private final BuilderType builderType;
    private final LANGUAGE language;

    /**
     * Instantiates a new Add if max command.
     *
     * @param controller  the controller
     * @param cmdReader   the cmd reader
     * @param fileReader  the file reader
     * @param builderType the builder type
     * @param language    the language
     */
    public AddIfMaxCommand(HumanController controller, BufferedReader cmdReader, BufferedReader fileReader, BuilderType builderType, LANGUAGE language) {
        this.controller = controller;
        this.cmdReader = cmdReader;
        this.fileReader = fileReader;
        this.builderType = builderType;
        this.language = language;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            throw new ArgumentException(getError("no_args", LANGUAGE.RU));
        }
        HumanBeingRequestDTO dtoMax = HumanBeingRequestDTOBuilder.build(cmdReader, fileReader, builderType, language);
        if (controller.isImpactSpeedMax(dtoMax)) {
            controller.createHuman(dtoMax);
        }
    }

    @Override
    public String description() {
        return getCommandDescription("add_if_max", LANGUAGE.RU);
    }
}
