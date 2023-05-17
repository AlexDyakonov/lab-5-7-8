package ru.home.app.server.commands.list;

import ru.home.app.server.commands.Command;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.server.exception.ValidationException;
import ru.home.app.server.services.builders.BuilderType;
import ru.home.app.server.services.builders.HumanBeingRequestDTOBuilder;
import ru.home.app.util.LANGUAGE;

import java.io.BufferedReader;

import static ru.home.app.util.Message.*;

/**
 * The type Update command. Update HumanBeing with id
 */
public class UpdateCommand implements Command {
    private final HumanController controller;
    private final BufferedReader cmdReader;
    private final BufferedReader fileReader;
    private final BuilderType builderType;
    private final LANGUAGE language;

    /**
     * Instantiates a new Update command.
     *
     * @param controller  the controller
     * @param cmdReader   the cmd reader
     * @param fileReader  the file reader
     * @param builderType the builder type
     * @param language    the language
     */
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
