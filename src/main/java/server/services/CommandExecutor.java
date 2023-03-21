package server.services;

import server.controller.HumanController;
import server.controller.HumanControllerImpl;
import server.exception.ApplicationException;
import server.exception.ArgumentException;
import server.exception.CommandException;
import server.model.dto.HumanBeingRequestDTO;
import server.services.builders.HumanBeingRequestDTOBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static client.ui.ConsoleColors.*;

/**
 * The type Command executor.
 */
public class CommandExecutor {

    private final HumanController controller;
    private final HistoryManager history;
    private final String file;
    private final BufferedReader reader;
    private final List<String> scriptHistory;

    /**
     * Instantiates a new Command executor.
     *
     * @param fileName      the file name
     * @param scriptHistory the script history
     */
    public CommandExecutor(String fileName, List<String> scriptHistory) {
        this.file = tildaResolver(fileName);
        this.scriptHistory = scriptHistory == null ? new ArrayList<>() : scriptHistory;
        this.controller = new HumanControllerImpl(file);
        this.history = new HistoryManager(15); // limit history size
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void checkCommandArg(String command, int numOfArgs){
        String[] commandArr = command.split(" ");
        if (numOfArgs == 0 && commandArr.length - 1 > 0) {
            throw new ArgumentException("Данная команда вызывается без агрументов");
        }
        if (commandArr.length - 1 < numOfArgs) {
            throw new ArgumentException("Количество аргументов в данной команде равно " + numOfArgs);
        }
    }
    private void executeScript(String scriptName, BufferedReader reader) {
        if (scriptHistory.contains(scriptName)){
            throw new ApplicationException(error("Обнаружена рекурсия"));
        }

        scriptHistory.add(scriptName);
        String command;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(scriptName))) {
            while (fileReader.ready()) {
                command = fileReader.readLine();
                if (!command.equals("execute_script")) {
                    executeCommand(command, reader, fileReader, BuilderType.FILE);
                } else {
                    String[] execute = command.split(" ");
                    executeScript(execute[1], reader);
                }
            }
        } catch (IOException e) {
            System.out.println(unsuccess("Ошибка выполнения скрипта " + scriptName));
        }
    }

    /**
     * Execute command.
     *
     * @param command     the command
     * @param reader      the reader
     * @param reader2     the reader 2
     * @param builderType the builder type
     */
    public void executeCommand(String command, BufferedReader reader, BufferedReader reader2, BuilderType builderType) {
        long id;
        try {
            String[] compositeCommand = command.split(" ");
            history.addCommandToHistory(compositeCommand[0]);
            if (compositeCommand.length > 1) {
                switch (compositeCommand[0]) {
                    case "update": // update id {element}
                        id = Long.parseLong(compositeCommand[1]);
                        if (controller.getHumanById(id) == null){
                            throw new ArgumentException(unsuccess("Объект с id: " + id + " не был найден."));
                        }
                        controller.updateHuman(HumanBeingRequestDTOBuilder.build(reader, reader2, builderType), id);
                        break;
                    case "remove_by_id": // remove_by_id id
                        id = Long.parseLong(compositeCommand[1]);
                        controller.deleteHumanById(id);
                        System.out.println(success("Объект с id: " + id + " был удален."));
                        break;
                    case "execute_script": // execute_script file_name
                        executeScript(compositeCommand[1], reader);
                        scriptHistory.clear();
                        break;
                    case "count_by_mood": //count_by_mood mood
                        checkCommandArg(command, 1);
                        System.out.println(controller.countByMood(compositeCommand[1]));
                        break;
                }
            } else {
                switch (command) {
                    case "add": // add {element}
                        HumanBeingRequestDTO dto = HumanBeingRequestDTOBuilder.build(reader, reader2, builderType);
                        controller.createHuman(dto);
                        break;
                    case "add_if_max": //add_if_max {element}
                        HumanBeingRequestDTO dtoMax = HumanBeingRequestDTOBuilder.build(reader, reader2, builderType);
                        if (isImpactSpeedMax(dtoMax)) {
                            controller.createHuman(dtoMax);
                        }
                        break;
                    case "add_if_min": // add_if_min {element}
                        HumanBeingRequestDTO dtoMin = HumanBeingRequestDTOBuilder.build(reader, reader2, builderType);
                        if (isImpactSpeedMin(dtoMin)) {
                            controller.createHuman(dtoMin);
                        }
                        break;
                    case "help":
                        System.out.println(controller.help());
                        break;
                    case "info":
                        System.out.println(controller.info());
                        break;
                    case "show":
                        controller.getAllHuman().forEach(System.out::println);
                        break;
                    case "clear":
                        controller.clear();
                        break;
                    case "save":
                        controller.save(file);
                        break;
                    case "history":
                        history.getHistoryListOfCommands().forEach(System.out::println);
                        break;
                    case "max_by_impact_speed":
                        System.out.println(controller.max_by_impact_speed());
                        break;
                    case "print_ascending":
                        controller.print_ascending().forEach(System.out::println);
                        System.out.println(success("Было успшно выведено " + controller.getAllHuman().size() + "элементов"));
                        break;
                    default:
                        System.out.println("Команда не распознана");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isImpactSpeedMax(HumanBeingRequestDTO dto) {
        return controller.isImpactSpeedMax(dto);
    }

    private boolean isImpactSpeedMin(HumanBeingRequestDTO dto) {
        return controller.isImpactSpeedMin(dto);
    }
    private String tildaResolver(String file) {
        if (file.startsWith("~")) {
            String rootDirectory = new File(file).getAbsolutePath();
            rootDirectory = rootDirectory.replace("~", "");
//            System.out.println(rootDirectory);
            file = rootDirectory;
        }
        return file;
    }
}
