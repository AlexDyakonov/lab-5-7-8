package ui;

import static ui.ConsoleColors.GREEN_BRIGHT;
import static ui.ConsoleColors.WHITE;

public class MenuConstants {

    public static final String HELP =
        GREEN_BRIGHT + "Добро пожаловать! Введите команду для продолжения\n" + GREEN_BRIGHT +
            "   help : " + WHITE + "вывести справку по доступным командам\n" + GREEN_BRIGHT +
            "   info : " + WHITE
            + "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n"
            + GREEN_BRIGHT +
            "   show : " + WHITE
            + "вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n"
            + GREEN_BRIGHT +
            "   add : " + WHITE + "добавить новый элемент в коллекцию\n" + GREEN_BRIGHT +
            "   update id : " + WHITE
            + "обновить значение элемента коллекции, id которого равен заданному\n"
            + GREEN_BRIGHT +
            "   remove_by_id id : " + WHITE + "удалить элемент из коллекции по его id\n"
            + GREEN_BRIGHT +
            "   clear : " + WHITE + "очистить коллекцию\n" + GREEN_BRIGHT +
            "   save : " + WHITE + "сохранить коллекцию в файл\n" + GREEN_BRIGHT +
            "   execute_script file_name : " + WHITE
            + "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n"
            + GREEN_BRIGHT +
            "   exit : " + WHITE + "завершить программу (без сохранения в файл)\n"
            + GREEN_BRIGHT +
            "   add_if_max {element} : " + WHITE
            + "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n"
            + GREEN_BRIGHT +
            "   add_if_min {element} : " + WHITE
            + "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n"
            + GREEN_BRIGHT +
            "   history : " + WHITE + "вывести последние 15 команд (без их аргументов)\n"
            + GREEN_BRIGHT +
            "   max_by_impact_speed : " + WHITE
            + "вывести любой объект из коллекции, значение поля impactSpeed которого является максимальным\n"
            + GREEN_BRIGHT +
            "   count_by_mood mood : " + WHITE
            + "вывести количество элементов, значение поля mood которых равно заданному\n"
            + GREEN_BRIGHT +
            "   print_ascending : " + WHITE
            + "вывести элементы коллекции в порядке возрастания\n";

    public static final int HISTORY_SIZE = 15;
}
