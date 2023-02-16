package command;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String,Command> commands = new HashMap<>();

    // Сюда добавляем команды
    // Исключения после команд посмотреть

    public CommandManager() {
        commands.put("add", new AddCommand());
        commands.put("clear", new ClearCommand());
        commands.put("info", new InfoCommand());
    }

    public void executeCommand(String[] args){
        commands.get(args[0]).execute(args);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
