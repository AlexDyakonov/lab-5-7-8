package server.commands.list;

import server.commands.Command;
import server.commands.Invoker;
import server.exception.ApplicationException;
import server.exception.ArgumentException;
import server.services.BuilderType;
import server.services.ScriptManager;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Logger;

import static client.ui.ConsoleColors.unsuccess;
import static server.services.LoggerManager.setupLogger;
import static util.Message.*;

public class ExecuteScriptCommand implements Command {
    private static final Logger logger = Logger.getLogger(ExecuteScriptCommand.class.getName());
    private final Invoker invoker;
    private ScriptManager scriptManager;
    private LANGUAGE language;

    public ExecuteScriptCommand(Invoker invoker, ScriptManager scriptManager, LANGUAGE language) {
        this.invoker = invoker;
        this.scriptManager = scriptManager;
        this.language = language;
        setupLogger(logger);
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            throw new ArgumentException(getError("one_arg", language));
        }
        scriptManager.addToScripts(args[1]);
        String command;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(args[1]));
            invoker.setFileReader(fileReader).setBuilderType(BuilderType.FILE).init();

            logger.info(getLog("executing_file").replace("%file%", Paths.get(args[1]).getFileName().toString()));

            while (fileReader.ready()) {
                command = fileReader.readLine();
                if (command.split(" ")[0].equals("execute_script")) {

                    logger.info(getLog("new_script"));

                    if (scriptManager.getScripts().contains(command.split(" ")[1])) {
                        throw new ApplicationException(getError("recursion_met", language));
                    }
                    scriptManager.addToScripts(command.split(" ")[1]);
                }
                invoker.execute(command);
            }

            invoker.setFileReader(null).setBuilderType(BuilderType.CMD).init();
            scriptManager.clearScripts();
            fileReader.close();

            System.out.println(getSuccessMessage("done", language));
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }

    @Override
    public String description() {
        return getCommandDescription("execute_script", language);
    }
}
