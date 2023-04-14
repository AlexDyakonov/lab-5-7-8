package server.commands.list;

import server.commands.Command;
import server.commands.Invoker;
import server.exception.ApplicationException;
import server.services.BuilderType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static client.ui.ConsoleColors.unsuccess;

public class ExecuteScriptCommand implements Command {
    private final Invoker invoker;

    public ExecuteScriptCommand(Invoker invoker) {
        this.invoker = invoker;
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
                } else if (invoker.getScriptHistory().contains(command.split(" ")[1])) {
                    throw new ApplicationException("Recursion");
                } else {
                    invoker.execute(command);
                }
            }
            invoker.getScriptHistory().clear();
            invoker.setBuilderType(BuilderType.CMD).init();
        } catch (IOException e) {
            System.out.println(unsuccess("Ошибка выполнения скрипта"));
        }
    }

    @Override
    public String description() {
        return null;
    }
}
