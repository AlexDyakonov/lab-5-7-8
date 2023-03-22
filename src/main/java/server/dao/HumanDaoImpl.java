package server.dao;

import server.db.DataBaseProvider;
import server.exception.FileException;
import server.mapper.HumanBeingMapper;
import server.model.HumanBeingModel;
import server.model.Mood;
import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static client.ui.ConsoleColors.success;
import static client.ui.ConsoleColors.unsuccess;

/**
 * The type Human dao.
 */
public class HumanDaoImpl implements HumanDao {

    private final DataBaseProvider source;

    /**
     * Instantiates a new Human dao.
     *
     * @param fileName the file name
     */
    public HumanDaoImpl(String fileName) {
        this.source = new DataBaseProvider(fileName);
    }

    @Override
    public HumanBeingResponseDTO getHumanById(Long id) {
        HumanBeingResponseDTO response = null;
        for (HumanBeingModel model : source.getDataBase()) {
            if (model.getId() == id) {
                response = HumanBeingMapper.fromModelToResponse(model);
            }
        }
        return response;
    }

    // show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
    @Override
    public List<HumanBeingResponseDTO> getAllHuman() {
        return source.getDataBase().stream().map(HumanBeingMapper::fromModelToResponse).collect(Collectors.toList());
    }

    @Override
    public Long createHuman(HumanBeingRequestDTO human) {
        return source.addHumanToDatabase(HumanBeingMapper.fromRequestToModel(human));
    }

    @Override
    public void deleteHumanById(Long id) {
        if (getHumanById(id) == null){
            System.out.println(unsuccess("Объект с id: " + id + " не был найден."));
        }
        for (HumanBeingModel human : source.getDataBase()) {
            if (human.getId() == id) {
                source.getDataBase().remove(human);
                System.out.println(success("Объект с id: " + id + " был удален."));
            }
        }
    }

    @Override
    public HumanBeingResponseDTO updateHuman(HumanBeingRequestDTO newHuman, Long id) {
        if (getHumanById(id) == null){
            System.out.println(unsuccess("Объект с id: " + id + " не был найден."));
        }
        for (HumanBeingModel human : source.getDataBase()) {
            if (human.getId() == id) {
                human.setId(id);
                human.setName(newHuman.getName());
                human.setCoordinates(newHuman.getCoordinates());
                human.setHasToothpick(newHuman.getHasToothpick());
                human.setRealHero(newHuman.getRealHero());
                human.setImpactSpeed(newHuman.getImpactSpeed());
                human.setMood(newHuman.getMood());
                human.setWeaponType(newHuman.getWeaponType());
                human.setCar(newHuman.getCar());
                System.out.println(success("Объект с id: " + id + " был обновлен."));
                return HumanBeingMapper.fromModelToResponse(human);
            }
        }
        return null;
    }

    @Override
    public String info() {
        return "\n Класс бд: " + source.getDataBase().getClass().getTypeName().split("\\.")[2] + " \n Создано: " + source.getCreationDate() + "\n Внутри элементов:" + source.getDataBase().size();
    }

    //clear : очистить коллекцию
    @Override
    public void clear() {
        int elemsBefore = source.getDataBase().size();
        source.getDataBase().clear();
        System.out.println(success("Было удалено " + elemsBefore + " элементов."));
    }

    // save : сохранить коллекцию в файл
    @Override
    public void save(String fileName) {
        try {
            source.save(fileName);
        } catch (FileException e){
            System.out.println(e.getMessage());
        }
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
        return HumanBeingMapper.fromModelToResponse(model);
    }

    // print_ascending : вывести элементы коллекции в порядке возрастания
    @Override
    public List<HumanBeingResponseDTO> print_ascending() {
        List<HumanBeingResponseDTO> list = new java.util.ArrayList<>(source.getDataBase().stream().map(HumanBeingMapper::fromModelToResponse).sorted().toList());
        Collections.reverse(list);
        return list;
    }

    // add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
    @Override
    public Long addIfMax(HumanBeingRequestDTO request) {
        return createHuman(request);
    }

    // add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
    @Override
    public Long addIfMin(HumanBeingRequestDTO request) {
        return createHuman(request);
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

    @Override
    public boolean isImpactSpeedMax(HumanBeingRequestDTO dto) {
        Set<HumanBeingModel> setFromDB = source.getDataBase();
        Float impSpeedDto = dto.getImpactSpeed();
        for (HumanBeingModel model : setFromDB) {
            if (model.getImpactSpeed() > impSpeedDto) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isImpactSpeedMin(HumanBeingRequestDTO dto) {
        Set<HumanBeingModel> setFromDB = source.getDataBase();
        Float impSpeedDto = dto.getImpactSpeed();
        for (HumanBeingModel model : setFromDB) {
            if (model.getImpactSpeed() < impSpeedDto) {
                return false;
            }
        }
        return true;
    }
}