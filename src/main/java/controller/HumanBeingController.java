package controller;

import model.*;

import java.util.List;
import java.util.Set;

public interface HumanBeingController {
    String info();
    Set<HumanBeing> show();
    HumanBeingResponseDTO addElementToCollection(HumanBeingRequestDTO humanBeingRequestDTO);
    HumanBeingResponseDTO updateById(String id, HumanBeingRequestDTO humanBeingRequestDTO);
    void removeById(String id);
    Set<HumanBeing> clear();
    void save();
    void executeScript(String fileName);
    void addIfMax(HumanBeingRequestDTO humanBeingRequestDTO);
    void addIfMin(HumanBeingRequestDTO humanBeingRequestDTO);
    HumanBeingResponseDTO maxByImpactSpeed();
    void countByMood(Mood mood);
    List<HumanBeing> printAscending();
    int getSize();
}
