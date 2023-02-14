package ui;

import controller.HumanBeingControllerImpl;
import dao.HumanBeingDAOImpl;
import model.*;
import services.HumanBeingServiceImpl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Задание: Сделать распознание введеной строки, чтобы можно было отличить команду и ее аргументы
public class ConsoleUI {
    private static final HumanBeingControllerImpl userController = new HumanBeingControllerImpl(new HumanBeingServiceImpl(new HumanBeingDAOImpl()));

    public static void menu(){
        System.out.println("Добро пожаловать! Введите команду для продолжения\n" +
                "   help : вывести справку по доступным командам\n" +
                "   info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "   show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "   add {name, X, Yd, realHero, hasToothpick, impactSpeed, soundtrackName, weaponType, mood, carName, cool}  : добавить новый элемент в коллекцию\n" +
                "   update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "   remove_by_id id : удалить элемент из коллекции по его id\n" +
                "   clear : очистить коллекцию\n" +
                "   save : сохранить коллекцию в файл\n" +
                "   execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "   exit : завершить программу (без сохранения в файл)\n" +
                "   add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "   add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                "   history : вывести последние 15 команд (без их аргументов)\n" +
                "   max_by_impact_speed : вывести любой объект из коллекции, значение поля impactSpeed которого является максимальным\n" +
                "   count_by_mood mood : вывести количество элементов, значение поля mood которых равно заданному\n" +
                "   print_ascending : вывести элементы коллекции в порядке возрастания\n");
    }
    private static Coordinates readCoords(){
        Coordinates coordinates = new Coordinates();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите координату х ");
            String xStr = reader.readLine();
            Integer x = Integer.parseInt(xStr);
            System.out.println("Введите координату у ");
            String yStr = reader.readLine();
            Double y = Double.parseDouble(yStr);
            coordinates.setX(x);
            coordinates.setY(y);
            return coordinates;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        return coordinates;
    }
    private static Boolean readBool(){
        boolean answer = false;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String ans = reader.readLine();
            answer = Boolean.parseBoolean(ans);
            return answer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void start() {
        menu();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String[] commandArr = reader.readLine().split(" ");
            String command = commandArr[0];

            List<String> commandsList = new ArrayList<>();

            String name;
            Coordinates coordinates = new Coordinates(1, 1d);
            boolean realHero;
            boolean hasToothpick;
            Float impactSpeed;
            String soundtrackName;
            WeaponType weaponType;
            Mood mood;
            Car car;

            while (!(command.equals("exit"))){
                commandsList.add(Arrays.toString(commandArr));
                switch (command) {
                    case "help":
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
                        name = commandArr[1];
                        soundtrackName = commandArr[2];

                        HumanBeingRequestDTO humanBeingRequestDTO = new HumanBeingRequestDTO(name, coordinates, true, true, 1F, soundtrackName, WeaponType.BAT, Mood.CALM, new Car("Bebra", true));
                        System.out.println(userController.addElementToCollection(humanBeingRequestDTO));
                        break;
                    case "update":
                        System.out.println("Введите id пользователя, данные которого вы хотите изменить:");
                        break;
                    case "remove_by_id":
                        System.out.println("Введите id пользователя, которого хотите удалить");
                        break;
                    case "clear":
                        userController.clear();
                        System.out.println("Коллекция успешно очищена.");
                        break;
                    case "save":
                        System.out.println("Список всех городов:");
                        break;
                    case "execute_script":
                        System.out.println("Скрипт");
                        break;
                    case "add_if_max":
                        System.out.println("Макс");
                        break;
                    case "add_if_min":
                        System.out.println("Мин");
                        break;
                    case "history":
                        if (commandsList.size() < 10){
                            System.out.println(commandsList.toString());
                        } else{
                            for(int i = 1; i <= 10; i++){
                                System.out.print(commandsList.get(commandsList.size() - i) + " ");
                            }
                        }
                        break;
                    case "max_by_impact_speed":
                        System.out.println("МаксБиИмпакт");
                        break;
                    case "count_by_mood":
                        System.out.println("mood");
                        break;
                    case "print_ascending":
                        System.out.println("print ascend");
                        break;
                    default:
                        System.out.println("Вы ввели значение не из меню");
                        break;
                }
                 commandArr = reader.readLine().split(" ");
                 command = commandArr[0];
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
