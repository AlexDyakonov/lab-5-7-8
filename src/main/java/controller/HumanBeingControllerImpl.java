package controller;

import exception.ValidationException;
import model.*;
import services.HumanBeingService;

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
    public String addElementToCollection(HumanBeingRequestDTO humanBeingRequestDTO) {
        return humanBeingService.addElementToCollection(humanBeingRequestDTO);
    }

    @Override
    public void updateById(Long id, HumanBeing humanBeing) {
        validate(id, this::validateId, "Invalid Id", new Throwable("id"));
        humanBeingService.updateById(id, humanBeing);
    }

    @Override
    public void removeById(Long id) {
        validate(id, this::validateId, "Invalid Id", new Throwable("id"));
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
    public void maxByImpactSpeed(HumanBeing humanBeing) {
        humanBeingService.maxByImpactSpeed(humanBeing);
    }

    @Override
    public void countByMood(HumanBeing humanBeing) {
        humanBeingService.countByMood(humanBeing);
    }

    @Override
    public void printAscending() {
        humanBeingService.printAscending();
    }

    //#TODO Изменить валидацию данных
    private boolean validateUserName(String userName){
        return (userName != null && !userName.trim().equals(""));
    }
    private boolean validateSoundtrackName(String soundtrackName){
        return (soundtrackName != null && !soundtrackName.trim().equals(""));
    }

    private boolean validateId(Long id){
        return (id != null && id > 0);
    }

    private <T> void validate(T object, Function<T, Boolean> validator, String errorMessage, Throwable cause){
        if (!validator.apply(object)){
            throw new ValidationException(errorMessage, cause);
        }
    }

}
