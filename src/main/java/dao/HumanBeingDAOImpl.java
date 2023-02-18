package dao;

import DataBase.*;
import model.*;

import java.util.*;

// Тут будем взаимодействовать со сетом из класса ДатаБейз
public class HumanBeingDAOImpl implements HumanBeingDAO {
    private final DataBase dataBase = new DataBaseImpl();
    private int findByMood(Mood mood){
        int cnt = 0;
        for (HumanBeing item : dataBase.getDataBase()){
            if (Objects.equals(item.getMood(), mood)){
                cnt++;
            }
        }
        return cnt;
    }

    private boolean findById(Long id){
        for (HumanBeing item : dataBase.getDataBase()){
            if (Objects.equals(item.getId(), id)){
                return true;
            }
        }
        return false;
    }

    private HumanBeing getById(Long id){
        HumanBeing humanBeing = null;
        for (HumanBeing item : dataBase.getDataBase()){
            if (Objects.equals(item.getId(), id)){
                humanBeing = item;
            }
        }
        return humanBeing;
    }

    @Override
    public String info() {
        try {
            String dbType = dataBase.getDataBase().getClass().toString();
            String dbCreationTime = dataBase.getCreationDate().toString();
            int dbLength = dataBase.getDataBase().size();
            return ("List type: " + dbType + "\n List creation time: " + dbCreationTime + "\n List size: " + dbLength);
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
        String name = humanBeingRequestDTO.getName();
        Coordinates coordinates = humanBeingRequestDTO.getCoordinates();
        Boolean realHero = humanBeingRequestDTO.getRealHero();
        Boolean hasToothpick = humanBeingRequestDTO.getHasToothpick();
        float impactSpeed = humanBeingRequestDTO.getImpactSpeed();
        String soundtrackName = humanBeingRequestDTO.getSoundtrackName();
        WeaponType weaponType = humanBeingRequestDTO.getWeaponType();
        Mood mood = humanBeingRequestDTO.getMood();
        Car car = humanBeingRequestDTO.getCar();
        HumanBeing humanBeing = new HumanBeing(name, coordinates, realHero, hasToothpick, impactSpeed, soundtrackName, weaponType, mood, car);
        dataBase.getDataBase().add(humanBeing);
        return new HumanBeingResponseDTO(humanBeing.getId(), name, coordinates, humanBeing.getCreationDate(), realHero, hasToothpick, impactSpeed, soundtrackName, weaponType, mood, car);
    }

    @Override
    public void updateById(Long id, HumanBeing humanBeing) {

    }

    @Override
    public void removeById(Long id) {
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

    @Override
    public void addIfMax(HumanBeing humanBeing) {

    }

    @Override
    public void addIfMin(HumanBeing humanBeing) {

    }

    @Override
    public void maxByImpactSpeed(HumanBeing humanBeing) {

    }

    @Override
    public void countByMood(Mood mood) {
        System.out.println(findByMood(mood) + " с настроением " + mood);
    }

    @Override
    public void printAscending() {

    }

    @Override
    public int getSize() {
        return dataBase.getDataBase().size();
    }
}
