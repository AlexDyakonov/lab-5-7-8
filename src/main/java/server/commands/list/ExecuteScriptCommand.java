package server.commands.list;

import server.commands.Command;
import server.commands.Invoker;
import server.exception.ApplicationException;
import server.services.BuilderType;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static client.ui.ConsoleColors.unsuccess;
import static util.Message.getCommandDescription;

public class ExecuteScriptCommand implements Command {
    private final Invoker invoker;
    private LANGUAGE language;

    public ExecuteScriptCommand(Invoker invoker, LANGUAGE language) {
        this.invoker = invoker;
        this.language = language;
    }

    @Override
    public void execute(String[] args) {
//        tildaSolver(args[1]);
        invoker.getScriptHistory().add(args[1]);
        String command;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(args[1]));
            invoker.setFileReader(fileReader).setBuilderType(BuilderType.FILE).init();
            while (fileReader.ready()) {
                command = fileReader.readLine();
                if (!command.split(" ")[0].equals("execute_script")) {
                    invoker.execute(command);
                } else {
                    invoker.getScriptHistory().add(command.split(" ")[1]);
                    System.out.println("---");
                    System.out.println(invoker.getScriptHistory());
                    System.out.println("---");
                    System.out.println("другой файл выполняется: ");
                    execute(command.split(" "));
                    if (invoker.getScriptHistory().contains(command.split(" ")[1])) {
                        throw new ApplicationException("Recursion");
                    }
                }
            }
            invoker.setBuilderType(BuilderType.CMD).init();
        } catch (ApplicationException e) {
            System.out.println(e.getMessage());
            invoker.getScriptHistory().clear();
        } catch (IOException e) {
            System.out.println(unsuccess(e.getMessage()));
        }
    }

    @Override
    public String description() {
        return getCommandDescription("execute_script", language);
    }
}
