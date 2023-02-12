package services;

import model.HumanBeing;
import model.HumanBeingRequestDTO;

public interface HumanBeingService {
    String info();
    void show();
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
