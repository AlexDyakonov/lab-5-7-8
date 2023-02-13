package dao;

import model.HumanBeing;
import model.HumanBeingRequestDTO;

import java.util.Set;

public interface HumanBeingDAO {
    String info();
    Set<HumanBeing> show();
    String addElementToCollection(HumanBeingRequestDTO humanBeingRequestDTO);
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
