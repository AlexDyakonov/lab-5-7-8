package server.services;

import server.dao.HumanDao;
import server.dao.HumanDaoImpl;
import server.model.Mood;
import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;

import java.util.List;

public class HumanServiceImpl implements HumanService {

    private final HumanDao repo;

    public HumanServiceImpl(String fileName) {
        this.repo = new HumanDaoImpl(fileName);
    }

    @Override
    public HumanBeingResponseDTO getHumanById(Long id) {
        return repo.getHumanById(id);
    }

    @Override
    public List<HumanBeingResponseDTO> getAllHuman() {
        return repo.getAllHuman();
    }

    @Override
    public Long createHuman(HumanBeingRequestDTO human) {
        return repo.createHuman(human);
    }

    @Override
    public void deleteHumanById(Long id) {
        repo.deleteHumanById(id);
    }

    @Override
    public HumanBeingResponseDTO updateHuman(HumanBeingRequestDTO newHuman, Long id) {
        return repo.updateHuman(newHuman, id);
    }

    @Override
    public String help() {
        return "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                "history : вывести последние 15 команд (без их аргументов)\n" +
                "max_by_impact_speed : вывести любой объект из коллекции, значение поля impactSpeed которого является максимальным\n" +
                "count_by_mood mood : вывести количество элементов, значение поля mood которых равно заданному\n" +
                "print_ascending : вывести элементы коллекции в порядке возрастания";
    }

    @Override
    public String info() {
        return repo.info();
    }

    @Override
    public void clear() {
        repo.clear();
    }

    @Override
    public void save(String fileName) {
        repo.save(fileName);
    }

    @Override
    public HumanBeingResponseDTO max_by_impact_speed() {
        return repo.max_by_impact_speed();
    }

    @Override
    public List<HumanBeingResponseDTO> print_ascending() {
        return repo.print_ascending();
    }

    @Override
    public Long addIfMax(HumanBeingRequestDTO request) {
        return repo.addIfMax(request);
    }

    @Override
    public Long addIfMin(HumanBeingRequestDTO request) {
        return repo.addIfMin(request);
    }

    @Override
    public int countByMood(Mood mood) {
        return repo.countByMood(mood);
    }
}
