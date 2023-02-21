package ui;

import controller.HumanBeingControllerImpl;
import dao.HumanBeingDAOImpl;
import exception.ArgumentException;
import model.HumanBeing;
import services.HumanBeingServiceImpl;
import utility.Asker;
import utility.HumanBeingRequestDTOBuilder;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ui.ConsoleColors.*;

public class ConsoleUI {
    // #TODO сделать обработку исключений при считывании базы данных \\ ВРОДЕ СДЕЛАЛ, НАДО ТЕСТЫ ПРОВЕСТИ
    private static final HumanBeingControllerImpl userController = new HumanBeingControllerImpl(new HumanBeingServiceImpl(new HumanBeingDAOImpl()));

    public static void menu(){
        System.out.println(GREEN_BRIGHT + "Добро пожаловать! Введите команду для продолжения\n" + GREEN_BRIGHT +
                "   help : "+ WHITE + "вывести справку по доступным командам\n" + GREEN_BRIGHT +
                "   info : "+ WHITE + "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" + GREEN_BRIGHT +
                "   show : "+ WHITE + "вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" + GREEN_BRIGHT +
                "   add : "+ WHITE + "добавить новый элемент в коллекцию\n" + GREEN_BRIGHT +
                "   update id : "+ WHITE + "обновить значение элемента коллекции, id которого равен заданному\n" + GREEN_BRIGHT +
                "   remove_by_id id : "+ WHITE + "удалить элемент из коллекции по его id\n" + GREEN_BRIGHT +
                "   clear : "+ WHITE + "очистить коллекцию\n" + GREEN_BRIGHT +
                "   save : "+ WHITE + "сохранить коллекцию в файл\n" + GREEN_BRIGHT +
                "   execute_script file_name : "+ WHITE + "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" + GREEN_BRIGHT +
                "   exit : "+ WHITE + "завершить программу (без сохранения в файл)\n" + GREEN_BRIGHT +
                "   add_if_max {element} : "+ WHITE + "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" + GREEN_BRIGHT +
                "   add_if_min {element} : "+ WHITE + "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" + GREEN_BRIGHT +
                "   history : "+ WHITE + "вывести последние 15 команд (без их аргументов)\n" + GREEN_BRIGHT +
                "   max_by_impact_speed : "+ WHITE + "вывести любой объект из коллекции, значение поля impactSpeed которого является максимальным\n" + GREEN_BRIGHT +
                "   count_by_mood mood : "+ WHITE + "вывести количество элементов, значение поля mood которых равно заданному\n" + GREEN_BRIGHT +
                "   print_ascending : "+ WHITE + "вывести элементы коллекции в порядке возрастания\n");
    }

    // #TODO реализовать НОРМАЛЬНЫЙ подсчет аргументов в команде
    private static void checkCommandArg(String[] commandArr, int numOfArgs){
        try {
            if (numOfArgs == 0 && commandArr.length - 1 > 0){
                throw new ArgumentException("Данная команда вызывается без агрументов");
            }
            if (commandArr.length - 1 < numOfArgs){
                throw new ArgumentException("Количество аргументов в данной команде равно " + numOfArgs);
            }
        } catch (ArgumentException e) {
            throw new RuntimeException(e);
        }

    }

    public static void start() {
        menu();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String[] commandArr = reader.readLine().split(" ");
            String command = commandArr[0];


            List<String> commandsList = new ArrayList<>();

            String id;


            while (!(command.equals("exit"))){
                commandsList.add(Arrays.toString(commandArr));
                HumanBeingRequestDTOBuilder humanBeingBuilder = new HumanBeingRequestDTOBuilder();
                try {
                switch (command) {
                    case "help":
                        checkCommandArg(commandArr, 0);
                        menu();
                        break;
                    case "info":
                        System.out.println(userController.info());
                        break;
                    case "show":
                        System.out.println(userController.show());
                        System.out.println("Выведены все элементы коллекции. ");
                        break;
                    case "add":
                        humanBeingBuilder = Asker.humanBeingRequestDTOBuilder();
                        System.out.println(userController.addElementToCollection(humanBeingBuilder.build()).toString() + " был добавлен");
                        break;
                    case "update":
                        try {
                            id = (commandArr[1]);
                            if (userController.findById(id)){
                                System.out.println(userController.updateById(id, Asker.humanBeingRequestDTOBuilder().build()).toString() + " был обновлен");
                            }else {
                                System.out.println("Объекта с id " + id + " не было найдено. Ничего не обновлено");
                            }
                        } catch (NumberFormatException ex){ // TODO посмотреть исключения, что тут может вылететь
                            System.out.println("Значение " + commandArr[1] + "не является id. Вызовите команду еще раз.");
                        }
                        break;
                    case "remove_by_id":
                        try {
                            id = (commandArr[1]);
                            userController.removeById(id);
                        } catch (NumberFormatException ex){ // TODO посмотреть исключения, что тут может вылететь
                            System.out.println("Значение " + commandArr[1] + "не является id. Вызовите команду еще раз.");
                        }
                        break;
                    case "clear":
                        int amount = userController.getSize();
                        userController.clear();
                        System.out.println("Коллекция успешно очищена. Было удалено " + amount + " элементов.");
                        break;
                    case "save":
                        System.out.println("Cохранить в csv файл");
                        userController.save();
                        break;
                    case "execute_script":
                        System.out.println("Скрипт");
                        break;
                    case "add_if_max":
                        userController.addIfMax(Asker.humanBeingRequestDTOBuilder().build());
                        break;
                    case "add_if_min":
                        userController.addIfMin(Asker.humanBeingRequestDTOBuilder().build());
                        break;
                    case "history":
                        if (commandsList.size() < 15){
                            System.out.println(commandsList);
                        } else{
                            for(int i = 1; i <= 15; i++){
                                System.out.print(commandsList.get(commandsList.size() - i) + " ");
                            }
                        }
                        break;
                    case "max_by_impact_speed":
                        userController.maxByImpactSpeed();
                        System.out.println(GREEN_BRIGHT + "Выведен элемент коллекции с максимальным Impact speed." + RESET);
                        break;
                    case "count_by_mood":
                        userController.countByMood(Asker.mood());
                        break;
                    case "print_ascending":
                        System.out.println(userController.printAscending());
                        System.out.println(GREEN_BRIGHT + "Выведены элементы коллекции по возрастанию." + RESET);
                        break;
                    default:
                        System.out.println("Вы ввели значение не из меню");
                        break;
                }
                 commandArr = reader.readLine().split(" ");
                 command = commandArr[0];
            } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }


        } catch (IOException e) {
            System.out.println("Ошибка открытия потока. Запустите программу еще раз.");;
        }
    }
}
