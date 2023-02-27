package utility;

import static ui.ConsoleColors.GREEN_BRIGHT;
import static ui.ConsoleColors.RESET;

import controller.HumanBeingControllerImpl;
import exception.FileException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fileLogic.FileAsker;
import ui.MenuConstants;

/**
 * The type Script executer.
 */
public class ScriptExecuter {

    private final HumanBeingControllerImpl userController;
    private final List<String> previousFiles;
    private final String filename;

    /**
     * Instantiates a new Script executer.
     *
     * @param userController the user controller
     * @param previousFiles  the previous files
     * @param filename       the filename
     */
    public ScriptExecuter(HumanBeingControllerImpl userController, List<String> previousFiles,
        String filename) {
        this.userController = userController;
        this.previousFiles = previousFiles == null ? new ArrayList<>() : previousFiles;
        this.filename = filename;
    }

    /**
     * Execute.
     */
    public void execute() {
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream(filename)))) {
            AbstractAsker asker = new FileAsker(reader);

            String[] commandArr = reader.readLine().split(" ");
            String command = commandArr[0];

            List<String> commandsList = new ArrayList<>();

            String id;

            while (!(command.equals("exit"))) {
                commandsList.add(Arrays.toString(commandArr));
                HumanBeingRequestDTOBuilder humanBeingBuilder;
                try {
                    switch (command) {
                        case "help":
                            System.out.println(MenuConstants.HELP);
                            break;
                        case "info":
                            System.out.println(userController.info());
                            break;
                        case "show":
                            System.out.println(userController.show());
                            System.out.println("Выведены все элементы коллекции. ");
                            break;
                        case "add":
                            humanBeingBuilder = asker.humanBeingRequestDTOBuilder();
                            System.out.println(
                                userController.addElementToCollection(humanBeingBuilder.build())
                                    .toString() + " был добавлен");
                            break;
                        case "update":
                            try {
                                id = (commandArr[1]);
                                if (userController.findById(id)) {
                                    System.out.println(userController.updateById(id,
                                            asker.humanBeingRequestDTOBuilder().build())
                                        .toString()
                                        + " был обновлен");
                                } else {
                                    System.out.println("Объекта с id " + id
                                        + " не было найдено. Ничего не обновлено");
                                }
                            } catch (NumberFormatException ex) { // TODO посмотреть исключения, что тут может вылететь
                                System.out.println("Значение " + commandArr[1]
                                    + "не является id. Вызовите команду еще раз.");
                            }
                            break;
                        case "remove_by_id":
                            try {
                                id = (commandArr[1]);
                                userController.removeById(id);
                            } catch (NumberFormatException ex) { // TODO посмотреть исключения, что тут может вылететь
                                System.out.println("Значение " + commandArr[1]
                                    + "не является id. Вызовите команду еще раз.");
                            }
                            break;
                        case "clear":
                            int amount = userController.getSize();
                            userController.clear();
                            System.out.println("Коллекция успешно очищена. Было удалено " + amount
                                + " элементов.");
                            break;
                        case "save":
                            System.out.println("Cохранить в csv файл");
                            userController.save();
                            break;
                        case "execute_script":
                            System.out.println("запуск скрипта");
                            if (previousFiles.contains(commandArr[1])) {
                                throw new FileException("infinity recursion");
                            }
                            previousFiles.add(filename);
                            new ScriptExecuter(userController, previousFiles, commandArr[1]).execute();
                            break;
                        case "add_if_max":
                            userController.addIfMax(
                                asker.humanBeingRequestDTOBuilder().build());
                            break;
                        case "add_if_min":
                            userController.addIfMin(
                                asker.humanBeingRequestDTOBuilder().build());
                            break;
                        case "history":
                            if (commandsList.size() < MenuConstants.HISTORY_SIZE) {
                                System.out.println(commandsList);
                            } else {
                                for (int i = 1; i <= MenuConstants.HISTORY_SIZE; i++) {
                                    System.out.print(
                                        commandsList.get(commandsList.size() - i) + " ");
                                }
                            }
                            break;
                        case "max_by_impact_speed":
                            userController.maxByImpactSpeed();
                            System.out.println(GREEN_BRIGHT
                                + "Выведен элемент коллекции с максимальным Impact speed." + RESET);
                            break;
                        case "count_by_mood":
                            userController.countByMood(asker.mood());
                            break;
                        case "print_ascending":
                            System.out.println(userController.printAscending());
                            System.out.println(
                                GREEN_BRIGHT + "Выведены элементы коллекции по возрастанию."
                                    + RESET);
                            break;
                        default:
                            System.out.println("Вы ввели значение не из меню");
                            break;
                    }

                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    String readline = reader.readLine();
                    if (readline == null) {
                        return;
                    }
                    commandArr = readline.split(" ");
                    command = commandArr[0];
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}