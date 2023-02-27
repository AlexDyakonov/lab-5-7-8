package dao;

import DataBase.*;
import model.*;

import java.util.*;

public class HumanBeingDAOImpl implements HumanBeingDAO {
    private final DataBase dataBase = new DataBaseImpl();
    private int amountWithMood(Mood mood){
        int cnt = 0;
        for (HumanBeing item : dataBase.getDataBase()){
            if (Objects.equals(item.getMood(), mood)){
                cnt++;
            }
        }
        return cnt;
    }
    @Override
    public boolean findById(String id){
        for (HumanBeing item : dataBase.getDataBase()){
            if (Objects.equals(item.getId(), id)){
                return true;
            }
        }
        return false;
    }

    private HumanBeing getById(String id){
        for (HumanBeing item : dataBase.getDataBase()){
            if (Objects.equals(item.getId(), id)){
                return item;
            }
        }
        return null;
    }

    @Override
    public String info() {
        try {
            String dbType = dataBase.getDataBase().getClass().toString();
            String dbCreationTime = dataBase.getCreationDate().toString();
            int dbLength = dataBase.getDataBase().size();
            return ("Тип коллекции: " + dbType + "\n Время создания коллекции: " + dbCreationTime + "\n Размер коллекции: " + dbLength);
        } catch (NullPointerException e){
            return "List was not created yet.";
        }
    }

    @Override
    public Set<HumanBeing> show() {
        return new HashSet<>(dataBase.getDataBase());
    }


    @Override
    public HumanBeingResponseDTO addElementToCollection(HumanBeingRequestDTO humanBeingRequestDTO) {
        HumanBeing humanBeing = new HumanBeing();
        dataBase.getDataBase().add(humanBeing.fromHumanRequestDTO(humanBeingRequestDTO));
        HumanBeingResponseDTO humanBeingResponseDTO = humanBeing.toResponseDTO(); //TODO придумать как пофиксить костыль с разными ID(сделать нормально)
        humanBeingResponseDTO.setId(humanBeing.getId());
        return humanBeingResponseDTO;
    }

    @Override
    public HumanBeingResponseDTO updateById(String id, HumanBeingRequestDTO humanBeingRequestDTO) {
        HumanBeing humanBeing = Objects.requireNonNull(getById(id));
        humanBeing.setName(humanBeingRequestDTO.getName());
        humanBeing.setCoordinates(humanBeingRequestDTO.getCoordinates());
            Objects.requireNonNull(getById(id)).setCar(humanBeingRequestDTO.getCar());
            Objects.requireNonNull(getById(id)).setMood(humanBeingRequestDTO.getMood());
            Objects.requireNonNull(getById(id)).setSoundtrackName(humanBeingRequestDTO.getSoundtrackName());
            Objects.requireNonNull(getById(id)).setWeaponType(humanBeingRequestDTO.getWeaponType());
            Objects.requireNonNull(getById(id)).setRealHero(humanBeingRequestDTO.getRealHero());
            Objects.requireNonNull(getById(id)).setHasToothpick(humanBeingRequestDTO.getHasToothpick());
            Objects.requireNonNull(getById(id)).setImpactSpeed(humanBeingRequestDTO.getImpactSpeed());
            HumanBeingResponseDTO humanBeingResponseDTO = Objects.requireNonNull(getById(id)).toResponseDTO(); //TODO придумать как пофиксить костыль с разными ID(сделать нормально)
            humanBeingResponseDTO.setId(Objects.requireNonNull(getById(id)).getId());
            return humanBeingResponseDTO;
    }

    @Override
    public void removeById(String id) {
        if (!findById(id)){
            System.out.println("HumanBeing с id " + id + " не найден");
        } else {
            dataBase.getDataBase().remove(getById(id));
            System.out.println("HumanBeing с id " + id + " удален.");
        }

    }

    @Override
    public Set<HumanBeing> clear() {
        dataBase.getDataBase().clear();
        return new LinkedHashSet<>(dataBase.getDataBase());
    }

    @Override
    public void save() {
        dataBase.saveDBToCSV();
    }

    @Override
    public void executeScript(String fileName) {

    }

    private boolean isImpactSpeedMin(Float impactSpeed){
        return toSortedList().get(toSortedList().size() - 1).getImpactSpeed() > impactSpeed;
    }
    private boolean isImpactSpeedMax(Float impactSpeed){
        return toSortedList().get(toSortedList().size() - 1).getImpactSpeed() < impactSpeed;
    }

    @Override
    public void addIfMax(HumanBeingRequestDTO humanBeingRequestDTO) {
        if (isImpactSpeedMax(humanBeingRequestDTO.getImpactSpeed())){
            addElementToCollection(humanBeingRequestDTO);
            System.out.println(humanBeingRequestDTO.toString() + " был добавлен в коллекцию.");
        } else {
            System.out.println("Элемент не был добавлен в коллекцию. Его значение impact speed меньше максимального в коллекции.");
        }
    }

    @Override
    public void addIfMin(HumanBeingRequestDTO humanBeingRequestDTO) {
        if (isImpactSpeedMin(humanBeingRequestDTO.getImpactSpeed())){
            addElementToCollection(humanBeingRequestDTO);
            System.out.println(humanBeingRequestDTO.toString() + " был добавлен в коллекцию.");
        } else {
            System.out.println("Элемент не был добавлен в коллекцию. Его значение impact speed больше минимального в коллекции.");
        }
    }

    @Override
    public HumanBeingResponseDTO maxByImpactSpeed() {
        System.out.println(toSortedList().get(0));
        return null;
    }

    @Override
    public void countByMood(Mood mood) {
        System.out.println(amountWithMood(mood) + " с настроением " + mood);
    }
    private List<HumanBeing> toSortedList(){
        List<HumanBeing> list = new ArrayList<>(dataBase.getDataBase());
        Collections.sort(list);
        return list;
    }

    @Override
    public List<HumanBeing> printAscending() {
        return toSortedList();
     }

    @Override
    public int getSize() {
        return dataBase.getDataBase().size();
    }
}
