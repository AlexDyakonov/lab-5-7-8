package server.controller;

import server.model.Mood;
import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;

import java.util.List;

public interface HumanController {

    // core commands

    HumanBeingResponseDTO getHumanById(Long id);
    List<HumanBeingResponseDTO> getAllHuman();
    Long createHuman(HumanBeingRequestDTO human);
    void deleteHumanById(Long id);
    HumanBeingResponseDTO updateHuman(HumanBeingRequestDTO newHuman, Long id );

    // additional commands

    String help();
    String info();
    void clear();
    void save(String fileName);
    HumanBeingResponseDTO max_by_impact_speed();
    List<HumanBeingResponseDTO> print_ascending();

    // additional composite commands

    Long addIfMax(HumanBeingRequestDTO request);
    Long addIfMin(HumanBeingRequestDTO request);
    int countByMood(Mood mood);
}
/*
help : вывести справку по доступным командам
info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
clear : очистить коллекцию
save : сохранить коллекцию в файл
exit : завершить программу (без сохранения в файл)
history : вывести последние 15 команд (без их аргументов)
max_by_impact_speed : вывести любой объект из коллекции, значение поля impactSpeed которого является максимальным
print_ascending : вывести элементы коллекции в порядке возрастания


update id {element} : обновить значение элемента коллекции, id которого равен заданному
add {element} : добавить новый элемент в коллекцию // createHuman
remove_by_id id : удалить элемент из коллекции по его id
execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
count_by_mood mood : вывести количество элементов, значение поля mood которых равно заданному
 */