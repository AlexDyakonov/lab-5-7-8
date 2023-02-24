package utility;

import controller.HumanBeingControllerImpl;
import dao.HumanBeingDAOImpl;
import exception.ApplicationException;
import exception.FileException;
import services.HumanBeingServiceImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScriptExecuter {
    private static final HumanBeingControllerImpl userController = new HumanBeingControllerImpl(new HumanBeingServiceImpl(new HumanBeingDAOImpl()));
    private static final List<String> commands = new ArrayList<>(Arrays.stream(new String[]{"help", "info", "show", "add", "update", "remove_by_id", "clear", "save", "execute_script", "exit", "add_if_max", "add_if_min", "history", "max_by_impact_speed", "count_by_mood", "print_ascending"}).toList());
    private static List<String> readLinesFromFile(String fileName){
        List<String> commandsFromFile = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null){
                if (!line.equals("")){
                    commandsFromFile.add(line.trim());
                }
                line = reader.readLine();
            }
            return commandsFromFile;
        } catch (FileNotFoundException e) {
            throw new FileException("Файл не был найден.");
        } catch (Exception e) {
            throw new ApplicationException("Не удалось открыть опток чтения файла.");
        }
    }
    private static List<Integer> findIndexOfCommands(String fileName){
        readLinesFromFile(fileName);
        return null;
    }
    private static boolean parseLineToCommand(String string){
        return commands.contains(string);
    }

    public static void main(String[] args) {
        System.out.println((readLinesFromFile("commands.txt")));
        System.out.println(parseLineToCommand("add"));
    }
}
