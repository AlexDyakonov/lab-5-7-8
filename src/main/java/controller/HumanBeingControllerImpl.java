package controller;

import exception.ValidationException;
import model.*;
import services.HumanBeingService;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

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
    public void updateById(String id, HumanBeing humanBeing) {
        humanBeingService.updateById(id, humanBeing);
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
    public void addIfMax(HumanBeing humanBeing) {
        humanBeingService.addIfMax(humanBeing);
    }

    @Override
    public void addIfMin(HumanBeing humanBeing) {
        humanBeingService.addIfMin(humanBeing);
    }

    @Override
    public HumanBeingResponseDTO maxByImpactSpeed(Float impactSpeed) {
        return humanBeingService.maxByImpactSpeed(impactSpeed);
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
