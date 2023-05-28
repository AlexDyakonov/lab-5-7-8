package ru.home.app.server.commands.list;

import ru.home.app.server.commands.Command;
import ru.home.app.server.commands.Invoker;
import ru.home.app.server.exception.ApplicationException;
import ru.home.app.server.exception.ArgumentException;
import ru.home.app.server.services.ScriptManager;
import ru.home.app.server.services.builders.BuilderType;
import ru.home.app.util.language.LANGUAGE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Logger;

import static ru.home.app.server.services.LoggerManager.setupLogger;
import static ru.home.app.util.Message.*;
import static ru.home.app.util.Parser.tildaResolver;

/**
 * The type Execute script command. Execute any script and execute commands inside.
 */
public class ExecuteScriptCommand implements Command {
    private static final Logger logger = Logger.getLogger(ExecuteScriptCommand.class.getName());
    private final Invoker invoker;
    private final ScriptManager scriptManager;
    private final LANGUAGE language;

    /**
     * Instantiates a new Execute script command.
     *
     * @param invoker       the invoker
     * @param scriptManager the script manager
     * @param language      the language
     */
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
        String command;
        String scriptName;
        String currentScript = tildaResolver(args[1]);
        scriptManager.addToScripts(currentScript);
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(currentScript));
            invoker.setFileReader(fileReader).setBuilderType(BuilderType.FILE).init();

            logger.info(getLog("executing_file").replace("%file%", Paths.get(currentScript).getFileName().toString()));

            while (fileReader.ready()) {
                command = fileReader.readLine();
                if (command.split(" ")[0].equals("execute_script")) {

                    logger.info(getLog("new_script"));

                    scriptName = tildaResolver(command.split(" ")[1]);

                    if (scriptManager.getScripts().contains(scriptName)) {
                        throw new ApplicationException(getError("recursion_met", language));
                    }
                    scriptManager.addToScripts(scriptName);
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
