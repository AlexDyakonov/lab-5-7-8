package services;

import model.HumanBeing;
import model.HumanBeingRequestDTO;
import model.HumanBeingResponseDTO;
import model.Mood;

import java.util.List;
import java.util.Set;

public interface HumanBeingService {
    String info();
    Set<HumanBeing> show();
    HumanBeingResponseDTO addElementToCollection(HumanBeingRequestDTO humanBeingRequestDTO);
    void updateById(String id, HumanBeing humanBeing);
    void removeById(String id);
    Set<HumanBeing> clear();
    void save();
    void executeScript(String fileName);
    void addIfMax(HumanBeing humanBeing);
    void addIfMin(HumanBeing humanBeing);
    HumanBeingResponseDTO maxByImpactSpeed(Float impactSpeed);
    void countByMood(Mood mood);
    List<HumanBeing> printAscending();
    int getSize();
}
