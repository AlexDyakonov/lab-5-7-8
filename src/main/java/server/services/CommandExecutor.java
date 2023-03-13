package server.services;

import client.ui.MenuConstants;
import client.utility.AbstractAsker;
import client.utility.HumanBeingRequestDTOBuilder;
import server.controller.HumanBeingController;
import server.controller.HumanBeingControllerImpl;
import server.exception.ArgumentException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static client.ui.ConsoleColors.GREEN_BRIGHT;
import static client.ui.ConsoleColors.RESET;


public class CommandExecutor {

    private final HumanBeingController humanBeingController;
    private final AbstractAsker asker;
    private HumanBeingRequestDTOBuilder humanBeingRequestDTOBuilder;
    private final List<String> commandsList;

    public CommandExecutor(AbstractAsker asker) {
        this.asker = asker;
        this.humanBeingController = new HumanBeingControllerImpl();
        this.humanBeingRequestDTOBuilder = new HumanBeingRequestDTOBuilder();
        this.commandsList = new ArrayList<>();
    }

    public void menu() {
        System.out.println(MenuConstants.HELP);
    }

    private void checkCommandArg(String[] commandArr, int numOfArgs) {
        if (numOfArgs == 0 && commandArr.length - 1 > 0) {
            throw new ArgumentException("Данная команда вызывается без агрументов");
        }
        if (commandArr.length - 1 < numOfArgs) {
            throw new ArgumentException("Количество аргументов в данной команде равно " + numOfArgs);
        }
    }

    public void execute(String inputCommand) {
        String[] commandArgs = inputCommand.split(" ");
        String command = commandArgs[0];
        commandsList.add(Arrays.toString(commandArgs));
        String id;

        switch (command) {
            case "help":
                checkCommandArg(commandArgs, 0);
                menu();
                break;
            case "info":
                checkCommandArg(commandArgs, 0);
                System.out.println(humanBeingController.info());
                break;
            case "show":
                checkCommandArg(commandArgs, 0);
                System.out.println(humanBeingController.show());
                System.out.println("Выведены все элементы коллекции. ");
                break;
            case "add":
                checkCommandArg(commandArgs, 0);
                humanBeingRequestDTOBuilder = asker.humanBeingRequestDTOBuilder();
                System.out.println(
                        humanBeingController.addElementToCollection(humanBeingRequestDTOBuilder.build())
                                .toString() + " был добавлен");
                break;
            case "update":
                checkCommandArg(commandArgs, 1);
                try {
                    id = (commandArgs[1]);
                    if (humanBeingController.findById(id)) {
                        System.out.println(humanBeingController.updateById(id,
                                        asker.humanBeingRequestDTOBuilder().build())
                                .toString()
                                + " был обновлен");
                    } else {
                        System.out.println("Объекта с id " + id
                                + " не было найдено. Ничего не обновлено");
                    }
                } catch (NumberFormatException ex) { // TODO посмотреть исключения, что тут может вылететь
                    System.out.println("Значение " + commandArgs[1]
                            + "не является id. Вызовите команду еще раз.");
                }
                break;
            case "remove_by_id":
                checkCommandArg(commandArgs, 1);
                try {
                    id = (commandArgs[1]);
                    humanBeingController.removeById(id);
                } catch (NumberFormatException ex) { // TODO посмотреть исключения, что тут может вылететь
                    System.out.println("Значение " + commandArgs[1]
                            + "не является id. Вызовите команду еще раз.");
                }
                break;
            case "clear":
                checkCommandArg(commandArgs, 0);
                int amount = humanBeingController.getSize();
                humanBeingController.clear();
                System.out.println("Коллекция успешно очищена. Было удалено " + amount
                        + " элементов.");
                break;
            case "save":
                checkCommandArg(commandArgs, 0);
                System.out.println("Cохранить в csv файл");
                humanBeingController.save();
                break;
            case "execute_script":
                checkCommandArg(commandArgs, 1);
                System.out.println("запуск скрипта");

                ScriptExecutor scriptExecutor = new ScriptExecutor();
                scriptExecutor.executeScript("file name from args");
                break;
            case "add_if_max":
                checkCommandArg(commandArgs, 0);
                humanBeingController.addIfMax(
                        asker.humanBeingRequestDTOBuilder().build());
                break;
            case "add_if_min":
                checkCommandArg(commandArgs, 0);
                humanBeingController.addIfMin(
                        asker.humanBeingRequestDTOBuilder().build());
                break;
            case "history":
                checkCommandArg(commandArgs, 0);
                if (commandsList.size() < MenuConstants.HISTORY_SIZE) {
                    System.out.println(commandsList);
                } else {
                    for (int i = 1; i <= MenuConstants.HISTORY_SIZE; i++) {
                        System.out.print(commandsList.get(commandsList.size() - i) + " ");
                    }
                }
                break;
            case "max_by_impact_speed":
                checkCommandArg(commandArgs, 0);
                humanBeingController.maxByImpactSpeed();
                System.out.println(GREEN_BRIGHT
                        + "Выведен элемент коллекции с максимальным Impact speed." + RESET);
                break;
            case "count_by_mood":
                checkCommandArg(commandArgs, 0);
                humanBeingController.countByMood(asker.mood());
                break;
            case "print_ascending":
                checkCommandArg(commandArgs, 0);
                System.out.println(humanBeingController.printAscending());
                System.out.println(
                        GREEN_BRIGHT + "Выведены элементы коллекции по возрастанию."
                                + RESET);
                break;
            default:
                System.out.println("Вы ввели значение не из меню");
                break;
        }
    }

    public List<String> getCommandsList() {
        return commandsList;
    }
}