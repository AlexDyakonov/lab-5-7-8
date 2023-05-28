package ru.home.app.server.commands.list;

import ru.home.app.server.commands.Command;
import ru.home.app.server.controller.HumanController;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.server.services.builders.BuilderType;
import ru.home.app.server.services.builders.HumanBeingRequestDTOBuilder;
import ru.home.app.util.language.LANGUAGE;

import java.io.BufferedReader;

import static ru.home.app.util.Message.getCommandDescription;
import static ru.home.app.util.Message.getError;

/**
 * The type Add command. Adds HumanBeing to db.
 */
public class AddCommand implements Command {
    private final LANGUAGE language;
    private final HumanController controller;
    private final BufferedReader cmdReader;
    private final BufferedReader fileReader;
    private final BuilderType builderType;

    /**
     * Instantiates a new Add command.
     *
     * @param controller  the controller
     * @param cmdReader   the cmd reader
     * @param fileReader  the file reader
     * @param builderType the builder type
     * @param language    the language
     */
    public AddCommand(HumanController controller, BufferedReader cmdReader, BufferedReader fileReader, BuilderType builderType, LANGUAGE language) {
        this.language = language;
        this.controller = controller;
        this.cmdReader = cmdReader;
        this.fileReader = fileReader;
        this.builderType = builderType;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            throw new ArgumentException(getError("no_args", language));
        }
        controller.createHuman(HumanBeingRequestDTOBuilder.build(cmdReader, fileReader, builderType, language));
    }

    @Override
    public String description() {
        return getCommandDescription("add", language);
    }
}
