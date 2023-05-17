package ru.home.app.ui;

import ru.home.app.server.authentication.Authentication;
import ru.home.app.server.commands.Invoker;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

import static ru.home.app.server.services.LoggerManager.setupLogger;

/**
 * The type Console ui.
 */
public class ConsoleUI {
    private static final Logger logger = Logger.getLogger(ConsoleUI.class.getName());

    static {
        setupLogger(logger);
    }

    private final Invoker invoker;

    /**
     * Instantiates a new Console ui.
     *
     * @param invoker the invoker
     */
    public ConsoleUI(Invoker invoker) {
        this.invoker = invoker;
    }

    /**
     * Start.
     */
    public void start() {
        String command;

        invoker.setFileReader(null);

        BufferedReader reader = invoker.getCmdReader();

        Authentication authentication = new Authentication(invoker.getController(), reader, invoker.getLanguage());
        authentication.start();

        invoker.setUserManager(authentication.getUserManager());


        try (reader) {
            System.out.println("Напишите help чтобы вывести все команды");
            while (!Objects.equals(command = reader.readLine(), "exit") && !Objects.equals(command, null)) {
                invoker.execute(command);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            logger.info(e.getMessage());
        }
    }

}
