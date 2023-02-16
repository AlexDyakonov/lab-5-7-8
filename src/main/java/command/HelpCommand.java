package command;

public class HelpCommand implements Command{
    private CommandManager commandManager = new CommandManager();

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute(String[] args) {
        System.out.println((commandManager.getCommands()));
    }
}
