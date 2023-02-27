package ui;

import static ui.ConsoleColors.GREEN_BRIGHT;
import static ui.ConsoleColors.RESET;

import controller.HumanBeingControllerImpl;
import exception.ArgumentException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exception.FileException;
import exception.ValidationException;
import utility.AbstractAsker;
import utility.HumanBeingRequestDTOBuilder;
import utility.ScriptExecuter;

/**
 * The type Command executor.
 */
public class CommandExecutor {

    private final HumanBeingControllerImpl userController;
    private final AbstractAsker asker;
    private final BufferedReader reader;

    /**
     * Instantiates a new Command executor.
     *
     * @param asker                the asker
     * @param humanBeingController the human being controller
     * @param reader               the reader
     */
    public CommandExecutor(AbstractAsker asker,
        HumanBeingControllerImpl humanBeingController,
        BufferedReader reader) {
        this.reader = reader;
        this.userController = humanBeingController;
        this.asker = asker;
    }

    /**
     * Menu.
     */
    public void menu() {
        System.out.println(MenuConstants.HELP);
    }

    private void checkCommandArg(String[] commandArr, int numOfArgs) {
        if (numOfArgs == 0 && commandArr.length - 1 > 0) {
            throw new ArgumentException("Данная команда вызывается без агрументов");
        }
        if (commandArr.length - 1 < numOfArgs) {
            throw new ArgumentException(
                "Количество аргументов в данной команде равно " + numOfArgs);
        }
    }

    /**
     * Execute.
     */
//TODO startExecuting();
    public void execute() {

            List<String> filenames = new ArrayList<>();

            List<String> commandsList = new ArrayList<>();

            String id;

            String[] commandArr;
            String command = "";

            while (!(command.equals("exit"))) {
                try {
                commandArr = reader.readLine().split(" ");
                command = commandArr[0];
                commandsList.add(Arrays.toString(commandArr));
                HumanBeingRequestDTOBuilder humanBeingBuilder;
                    switch (command) {
                        case "help":
                            checkCommandArg(commandArr, 0);
                            menu();
                            break;
                        case "info":
                            checkCommandArg(commandArr, 0);
                            System.out.println(userController.info());
                            break;
                        case "show":
                            checkCommandArg(commandArr, 0);
                            System.out.println(userController.show());
                            System.out.println("Выведены все элементы коллекции. ");
                            break;
                        case "add":
                            checkCommandArg(commandArr, 0);
                            humanBeingBuilder = asker.humanBeingRequestDTOBuilder();
                            System.out.println(
                                userController.addElementToCollection(humanBeingBuilder.build())
                                    .toString() + " был добавлен");
                            break;
                        case "update":
                            checkCommandArg(commandArr, 1);
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
                            checkCommandArg(commandArr, 1);
                            try {
                                id = (commandArr[1]);
                                userController.removeById(id);
                            } catch (NumberFormatException ex) { // TODO посмотреть исключения, что тут может вылететь
                                System.out.println("Значение " + commandArr[1]
                                    + "не является id. Вызовите команду еще раз.");
                            }
                            break;
                        case "clear":
                            checkCommandArg(commandArr, 0);
                            int amount = userController.getSize();
                            userController.clear();
                            System.out.println("Коллекция успешно очищена. Было удалено " + amount
                                + " элементов.");
                            break;
                        case "save":
                            checkCommandArg(commandArr, 0);
                            System.out.println("Cохранить в csv файл");
                            userController.save();
                            break;
                        case "execute_script":
                            checkCommandArg(commandArr, 1);
                            System.out.println("запуск скрипта");
                            new ScriptExecuter(userController, filenames, commandArr[1]).execute();
                            //TODO попробовтаь перенести логику скриптЭкзекутера сюда
                            break;
                        case "add_if_max":
                            checkCommandArg(commandArr, 0);
                            userController.addIfMax(
                                asker.humanBeingRequestDTOBuilder().build());
                            break;
                        case "add_if_min":
                            checkCommandArg(commandArr, 0);
                            userController.addIfMin(
                                asker.humanBeingRequestDTOBuilder().build());
                            break;
                        case "history":
                            checkCommandArg(commandArr, 0);
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
                            checkCommandArg(commandArr, 0);
                            userController.maxByImpactSpeed();
                            System.out.println(GREEN_BRIGHT
                                + "Выведен элемент коллекции с максимальным Impact speed." + RESET);
                            break;
                        case "count_by_mood":
                            checkCommandArg(commandArr, 0);
                            userController.countByMood(asker.mood());
                            break;
                        case "print_ascending":
                            checkCommandArg(commandArr, 0);
                            System.out.println(userController.printAscending());
                            System.out.println(
                                GREEN_BRIGHT + "Выведены элементы коллекции по возрастанию."
                                    + RESET);
                            break;
                        default:
                            System.out.println("Вы ввели значение не из меню");
                            break;
                    }
                } catch (ArgumentException | FileException | ValidationException e) {
                    System.out.println(e.getMessage()); //TODO спросить про общий на свои
                } catch (IOException e){
                    System.out.println("Ошибка потока чтения. Запустите еще раз.");
                    break;
            }
        }
    }
}