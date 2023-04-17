package client.ui;
import server.commands.Invoker;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import static server.validation.Validation.validateFile;
import static util.Parser.tildaResolver;

public class NewUI {
    private final String file;
    private final Invoker invoker;

    /**
     * Instantiates a new Console ui.
     *
     * @param fileName the file name
     * @param invoker
     */
    public NewUI(String fileName, Invoker invoker) {
        validateFile(fileName, invoker.getLanguage());
        fileName = tildaResolver(fileName);
        this.file = fileName;
        this.invoker = invoker;
    }

    /**
     * Start.
     */
    public void start() {
//        System.out.println(MenuConstants.HELLO + MenuConstants.HELP);
        String command;
        System.out.println("Напишите help чтобы вывести все команды");

        invoker.setFileReader(null);

        BufferedReader reader = invoker.getCmdReader();

        try (reader) {
            while (!Objects.equals(command = reader.readLine(), "exit") && !Objects.equals(command, null)) {
                invoker.execute(command);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
