package ru.home.app.server.commands;

/**
 * The interface Command.
 */
public interface Command {
    /**
     * Execute.
     *
     * @param args the args
     */
    void execute(String[] args);

    /**
     * Description string.
     *
     * @return the string
     */
    String description();
}
