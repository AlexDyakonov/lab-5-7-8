package controller;

import exception.ValidationException;
import model.HumanBeing;
import services.UserService;

import java.util.Objects;
import java.util.function.Function;

public class UserControllerImpl implements UserController{
    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = Objects.requireNonNull(userService, "User service must be provided.");
    }

    @Override
    public void info() {
        userService.info();
    }

    @Override
    public void show() {
        userService.show();
    }

    @Override
    public void addElementToCollection(HumanBeing humanBeing) {
        validate(humanBeing.getName(), this ::validateUserName, "Invalid humanBeing name");
        userService.addElementToCollection(humanBeing);
    }

    @Override
    public void updateById(Long id, HumanBeing humanBeing) {
        validate(id, this::validateId, "Invalid Id");
        userService.updateById(id, humanBeing);
    }

    @Override
    public void removeById(Long id) {
        validate(id, this::validateId, "Invalid Id");
        userService.removeById(id);
    }

    @Override
    public void clear() {
        userService.clear();
    }

    @Override
    public void save() {
        userService.save();
    }

    @Override
    public void executeScript(String fileName) {
        userService.executeScript(fileName);
    }

    @Override
    public void addIfMax(HumanBeing humanBeing) {
        userService.addIfMax(humanBeing);
    }

    @Override
    public void addIfMin(HumanBeing humanBeing) {
        userService.addIfMin(humanBeing);
    }

    @Override
    public void maxByImpactSpeed(HumanBeing humanBeing) {
        userService.maxByImpactSpeed(humanBeing);
    }

    @Override
    public void countByMood(HumanBeing humanBeing) {
        userService.countByMood(humanBeing);
    }

    @Override
    public void printAscending() {
        userService.printAscending();
    }
    private boolean validateUserName(String userName){
        return (userName != null && !userName.equals(""));
    }

    private boolean validateId(Long id){
        return (id != null && id > 0);
    }

    private <T> void validate(T object, Function<T, Boolean> validator, String errorMessage){
        if (!validator.apply(object)){
            throw new ValidationException(errorMessage);
        }
    }

}
