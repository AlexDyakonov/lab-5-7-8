package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

public class ConsoleUI {
    public static void menu(){
        System.out.println("Добро пожаловать! Введите команду для продолжения\n" +
                "   help : вывести справку по доступным командам\n" +
                "   info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "   show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "   add {element} : добавить новый элемент в коллекцию\n" +
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
    public static void start() {
        menu();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String command = "";
            while (!(command = reader.readLine()).equals("exit")){
                switch (command) {
                    case "help":
                        menu();
                        break;
                    case "show":
                        System.out.println("Для добавления пользователя введите имя");
                        break;
                    case "add":
                        System.out.println("Введите id пользователя: ");
                        break;
                    case "update":
                        System.out.println("Введите id пользователя, данные которого вы хотите изменить:");
                        break;
                    case "remove_by_id":
                        System.out.println("Введите id пользователя, которого хотите удалить");
                        break;
                    case "clear":
                        System.out.println("Список всех пользователей:");
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
                        System.out.println("История");
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
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
