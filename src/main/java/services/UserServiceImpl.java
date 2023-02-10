package services;

import dao.UserDAO;
import model.HumanBeing;

public class UserServiceImpl implements UserService{
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void info() {
        userDAO.info();
    }

    @Override
    public void show() {
        userDAO.show();
    }

    @Override
    public void addElementToCollection(HumanBeing humanBeing) {
        userDAO.addElementToCollection(humanBeing);
    }

    @Override
    public void updateById(Long id, HumanBeing humanBeing) {
        userDAO.updateById(id, humanBeing);
    }

    @Override
    public void removeById(Long id) {
        userDAO.removeById(id);
    }

    @Override
    public void clear() {
        userDAO.clear();
    }

    @Override
    public void save() {
        userDAO.save();
    }

    @Override
    public void executeScript(String fileName) {
        userDAO.executeScript(fileName);
    }

    @Override
    public void addIfMax(HumanBeing humanBeing) {
        userDAO.addIfMax(humanBeing);
    }

    @Override
    public void addIfMin(HumanBeing humanBeing) {
        userDAO.addIfMin(humanBeing);
    }

    @Override
    public void maxByImpactSpeed(HumanBeing humanBeing) {
        userDAO.maxByImpactSpeed(humanBeing);
    }

    @Override
    public void countByMood(HumanBeing humanBeing) {
        userDAO.countByMood(humanBeing);
    }

    @Override
    public void printAscending() {
        userDAO.printAscending();
    }
}
