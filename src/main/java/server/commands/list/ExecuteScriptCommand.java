package server.commands.list;

import server.commands.Command;
import server.commands.Invoker;
import server.exception.ApplicationException;
import server.services.BuilderType;
import server.services.ScriptManager;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static client.ui.ConsoleColors.unsuccess;
import static util.Message.getCommandDescription;

public class ExecuteScriptCommand implements Command {
    private final Invoker invoker;
    private ScriptManager scriptManager;
    private LANGUAGE language;

    public ExecuteScriptCommand(Invoker invoker, ScriptManager scriptManager, LANGUAGE language) {
        this.invoker = invoker;
        this.scriptManager = scriptManager;
        this.language = language;
    }

    @Override
    public void execute(String[] args) {
//        tildaSolver(args[1]);
        scriptManager.addToScripts(args[1]);
        String command;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(args[1]));
            invoker.setFileReader(fileReader).setBuilderType(BuilderType.FILE).init();
            while (fileReader.ready()) {
                command = fileReader.readLine();
                if (command.split(" ")[0].equals("execute_script")) {
                    if (scriptManager.getScripts().contains(command.split(" ")[1])) {
                        throw new ApplicationException("RECURSION");
                    }
                    scriptManager.addToScripts(command.split(" ")[1]);
                    System.out.println("---");
                    System.out.println(scriptManager.getScripts());
                    System.out.println("---");
                    System.out.println("другой файл выполняется: ");
                }
                invoker.execute(command);
            }
            invoker.setBuilderType(BuilderType.CMD).init();
            invoker.setFileReader(null);
            scriptManager.clearScripts();
            fileReader.close();
        } catch (ApplicationException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(unsuccess(e.getMessage()));
        }
    }

    @Override
    public String description() {
        return getCommandDescription("execute_script", language);
    }
}
