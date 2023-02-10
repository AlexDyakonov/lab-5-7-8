package dao;

import model.HumanBeing;

public interface UserDAO {
    void info();
    void show();
    void addElementToCollection(HumanBeing humanBeing);
    void updateById(Long id, HumanBeing humanBeing);
    void removeById(Long id);
    void clear();
    void save();
    void executeScript(String fileName);
    void addIfMax(HumanBeing humanBeing);
    void addIfMin(HumanBeing humanBeing);
    void maxByImpactSpeed(HumanBeing humanBeing);
    void countByMood(HumanBeing humanBeing);
    void printAscending();
}
