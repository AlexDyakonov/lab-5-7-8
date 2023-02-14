package dao;

import DataBase.*;
import model.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

// Тут будем взаимодействовать со сетом из класса ДатаБейз
public class HumanBeingDAOImpl implements HumanBeingDAO {
    private final DataBase dataBase = new DataBaseImpl();

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
    public String addElementToCollection(HumanBeingRequestDTO humanBeingRequestDTO) {
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
        return (humanBeing.toString() + " has been added to List");
    }

    @Override
    public void updateById(Long id, HumanBeing humanBeing) {

    }

    @Override
    public void removeById(Long id) {
//        dataBase.getDataBase().remove();
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
    public void countByMood(HumanBeing humanBeing) {

    }

    @Override
    public void printAscending() {

    }
}
