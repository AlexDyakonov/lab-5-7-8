package server.services;

import server.controller.HumanController;
import server.controller.HumanControllerImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

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

    private void executeScript(String scriptName) {
        String command;
        try (BufferedReader bf = new BufferedReader(new FileReader(scriptName))) {
            while (bf.ready()) {
                command = bf.readLine();
                if (!command.equals("execute_script")) {
                    executeCommand(command);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка выполнения скрипта " + scriptName);
        }
    }

    public void executeCommand(String command) {
        Long id;
        try {
            while (!Objects.equals(command = reader.readLine(), "exit")) {
                String[] compositeCommand = command.split(" ");
                history.addCommandToHistory(compositeCommand[0]);
                if (compositeCommand.length > 1) {
                    switch (compositeCommand[1]) {
                        case "add": // add {element}
                            break;
                        case "update": // update id {element}
                            break;
                        case "remove_by_id": // remove_by_id id
                            System.out.println("Введите id");
                            id = Long.parseLong(reader.readLine());
                            controller.deleteHumanById(id);
                            break;
                        case "execute_script": // execute_script file_name
                            System.out.println("Введите название файла скрипта: ");
                            executeScript(reader.readLine());
                            break;
                        case "add_if_max": //add_if_max {element}
                            break;
                        case "add_if_min": // add_if_min {element}
                            break;
                        case "count_by_mood": //count_by_mood mood
                            break;
                    }

                } else {
                    switch (command) {
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
                            break;
                        default:
                            System.out.println("Команда не распознана");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
