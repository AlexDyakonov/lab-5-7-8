package server.services;

import server.controller.HumanController;
import server.controller.HumanControllerImpl;
import server.exception.ArgumentException;
import server.model.dto.HumanBeingRequestDTO;
import server.services.builders.HumanBeingRequestDTOBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static client.ui.ConsoleColors.success;
import static client.ui.ConsoleColors.unsuccess;

public class CommandExecutor {

    private final HumanController controller;
    private final HistoryManager history;
    private final String file;
    private final BufferedReader reader;

    public CommandExecutor(String fileName) {
        this.file = fileName;
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
            throw new ArgumentException(
                    "Количество аргументов в данной команде равно " + numOfArgs);
        }
    }
    //todo
    private void executeScript(String scriptName, BufferedReader reader) {
        String command;
        try (BufferedReader bf = new BufferedReader(new FileReader(scriptName))) {
            while (bf.ready()) {
                command = bf.readLine();
                if (!command.equals("execute_script")) {
                    executeCommand(command, reader);
                }
            }
        } catch (IOException e) {
            System.out.println(unsuccess("Ошибка выполнения скрипта " + scriptName));
        }
    }

    public void executeCommand(String command, BufferedReader reader) {
        Long id;
        try {
            String[] compositeCommand = command.split(" ");
            history.addCommandToHistory(compositeCommand[0]);
            if (compositeCommand.length > 1) {
                switch (compositeCommand[0]) {
                    case "update": // update id {element}
                        checkCommandArg(command, 1);
                        controller.updateHuman(HumanBeingRequestDTOBuilder.build(reader), Long.parseLong(compositeCommand[1]));
                        break;
                    case "remove_by_id": // remove_by_id id
                        checkCommandArg(command, 1);
                        controller.deleteHumanById(Long.parseLong(compositeCommand[1]));
                        break;
                    case "execute_script": // execute_script file_name
                        checkCommandArg(command, 1);
                        executeScript(compositeCommand[1], reader);
                        break;
                    case "add_if_max": //add_if_max {element}
                        checkCommandArg(command, 1);
                        HumanBeingRequestDTO dtoMax = HumanBeingRequestDTOBuilder.build(reader);
                        if (isImpactSpeedMax(dtoMax)) {
                            controller.createHuman(dtoMax);
                        }
                        break;
                    case "add_if_min": // add_if_min {element}
                        checkCommandArg(command, 1);
                        HumanBeingRequestDTO dtoMin = HumanBeingRequestDTOBuilder.build(reader);
                        if (isImpactSpeedMin(dtoMin)) {
                            controller.createHuman(dtoMin);
                        }
                        break;
                    case "count_by_mood": //count_by_mood mood
                        checkCommandArg(command, 1);
                        System.out.println(controller.countByMood(compositeCommand[1]));
                        break;
                }
            } else {
                switch (command) {
                    case "add": // add {element}
                        HumanBeingRequestDTO dto = HumanBeingRequestDTOBuilder.build(reader);
                        controller.createHuman(dto);
                        break;
                    case "help":
                        checkCommandArg(command, 0);
                        System.out.println(controller.help());
                        break;
                    case "info":
                        checkCommandArg(command, 0);
                        System.out.println(controller.info());
                        break;
                    case "show":
                        checkCommandArg(command, 0);
                        controller.getAllHuman().forEach(System.out::println);
                        break;
                    case "clear":
                        checkCommandArg(command, 0);
                        controller.clear();
                        break;
                    case "save":
                        checkCommandArg(command, 0);
                        controller.save(file);
                        break;
                    case "history":
                        checkCommandArg(command, 0);
                        history.getHistoryListOfCommands().forEach(System.out::println);
                        break;
                    case "max_by_impact_speed":
                        checkCommandArg(command, 0);
                        System.out.println(controller.max_by_impact_speed());
                        break;
                    case "print_ascending":
                        checkCommandArg(command, 0);
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
}
