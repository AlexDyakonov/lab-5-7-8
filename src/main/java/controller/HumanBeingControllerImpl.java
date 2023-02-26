package controller;

import model.*;
import services.HumanBeingService;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Deprecated
public class HumanBeingControllerImpl implements HumanBeingController {
    //Тут реализуем валидацию данных
    private final HumanBeingService humanBeingService;

    public HumanBeingControllerImpl(HumanBeingService userService) {
        this.humanBeingService = Objects.requireNonNull(userService, "User service must be provided.");
    }

    @Override
    public String  info() {
        return humanBeingService.info();
    }

    @Override
    public Set<HumanBeing> show() {
        return humanBeingService.show();
    }

    @Override
    public HumanBeingResponseDTO addElementToCollection(HumanBeingRequestDTO humanBeingRequestDTO) {
        return humanBeingService.addElementToCollection(humanBeingRequestDTO);
    }

    @Override
    public boolean findById(String id) {
        return humanBeingService.findById(id);
    }

    @Override
    public HumanBeingResponseDTO updateById(String id, HumanBeingRequestDTO humanBeingRequestDTO) {
        return humanBeingService.updateById(id, humanBeingRequestDTO);
    }

    @Override
    public void removeById(String id) {
        humanBeingService.removeById(id);
    }

    @Override
    public Set<HumanBeing> clear() {
        return humanBeingService.clear();
    }

    @Override
    public void save() {
        humanBeingService.save();
    }

    @Override
    public void executeScript(String fileName) {
        humanBeingService.executeScript(fileName);
    }

    @Override
    public void addIfMax(HumanBeingRequestDTO humanBeingRequestDTO) {
        humanBeingService.addIfMax(humanBeingRequestDTO);
    }

    @Override
    public void addIfMin(HumanBeingRequestDTO humanBeingRequestDTO) {
        humanBeingService.addIfMin(humanBeingRequestDTO);
    }

    @Override
    public HumanBeingResponseDTO maxByImpactSpeed() {
        return humanBeingService.maxByImpactSpeed();
    }

    @Override
    public void countByMood(Mood mood) {
        humanBeingService.countByMood(mood);
    }

    @Override
    public List<HumanBeing> printAscending() {
        return humanBeingService.printAscending();
    }

    @Override
    public int getSize() {
        return humanBeingService.getSize();
    }


}
