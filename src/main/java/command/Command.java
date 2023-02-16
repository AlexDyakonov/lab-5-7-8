package command;

public interface Command {
    String getName();
    String getDescription();
    void execute(String[] args);
}
