package server.dao;

import server.db.DataBaseProvider;
import server.mapper.HumanBeingMapper;
import server.model.HumanBeingModel;
import server.model.Mood;
import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HumanDaoImpl implements HumanDao {

    private final DataBaseProvider source;
    private final HumanBeingMapper mapper;

    public HumanDaoImpl(String fileName) {
        this.source = new DataBaseProvider(fileName);
        this.mapper = new HumanBeingMapper();
    }

    @Override
    public HumanBeingResponseDTO getHumanById(Long id) {
        HumanBeingResponseDTO response = null;
        for (HumanBeingModel model : source.getDataBase()) {
            if (model.getId() == id) {
                response = mapper.fromModelToResponse(model);
            }
        }
        return response;
    }

    // show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
    @Override
    public List<HumanBeingResponseDTO> getAllHuman() {
        return source.getDataBase().stream().map(mapper::fromModelToResponse).collect(Collectors.toList());
    }

    @Override
    public Long createHuman(HumanBeingRequestDTO human) {
        return source.addHumanToDatabase(mapper.fromRequestToModel(human));
    }

    @Override
    public void deleteHumanById(Long id) {
        for (HumanBeingModel human : source.getDataBase()) {
            if (human.getId() == id) {
                source.getDataBase().remove(human);
            }
        }
    }

    @Override
    public HumanBeingResponseDTO updateHuman(HumanBeingRequestDTO newHuman, Long id) {
        return null;
    }

    // info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
    @Override
    public String info() {
        return source.getDataBase().getClass() + " " + source.getCreationDate() + " " + source.getDataBase().size();
    }

    //clear : очистить коллекцию
    @Override
    public void clear() {
        source.getDataBase().clear();
    }

    // save : сохранить коллекцию в файл
    @Override
    public void save(String fileName) {
        source.save(fileName);
    }

    // max_by_impact_speed : вывести любой объект из коллекции, значение поля impactSpeed которого является максимальным
    @Override
    public HumanBeingResponseDTO max_by_impact_speed() {
        HumanBeingModel model = null;
        float max = 0;
        for (HumanBeingModel m : source.getDataBase()) {
            if (m.getImpactSpeed() > max) {
                max = m.getImpactSpeed();
                model = m;
            }
        }
        return mapper.fromModelToResponse(model);
    }

    // print_ascending : вывести элементы коллекции в порядке возрастания
    @Override
    public List<HumanBeingResponseDTO> print_ascending() {
        List<HumanBeingResponseDTO> list = source.getDataBase().stream().map(mapper::fromModelToResponse).sorted().toList();
        Collections.reverse(list);
        return list;
    }

    // add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
    @Override
    public Long addIfMax(HumanBeingRequestDTO request) {
        // о каком значении речь?
        return null;
    }

    // add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
    @Override
    public Long addIfMin(HumanBeingRequestDTO request) {
        // о каком значении речь?
        return null;
    }

    // count_by_mood mood : вывести количество элементов, значение поля mood которых равно заданному
    @Override
    public int countByMood(Mood mood) {
        int count = 0;
        for (HumanBeingModel model : source.getDataBase()) {
            if (model.getMood().equals(mood)) {
                count++;
            }
        }
        return count;
    }
}
/*
help : вывести справку по доступным командам
info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
add {element} : добавить новый элемент в коллекцию
update id {element} : обновить значение элемента коллекции, id которого равен заданному
remove_by_id id : удалить элемент из коллекции по его id
clear : очистить коллекцию
save : сохранить коллекцию в файл
execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
exit : завершить программу (без сохранения в файл)
add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
history : вывести последние 15 команд (без их аргументов)
max_by_impact_speed : вывести любой объект из коллекции, значение поля impactSpeed которого является максимальным
count_by_mood mood : вывести количество элементов, значение поля mood которых равно заданному
print_ascending : вывести элементы коллекции в порядке возрастания
 */
