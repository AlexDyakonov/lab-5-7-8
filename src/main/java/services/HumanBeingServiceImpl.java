package services;

import dao.HumanBeingDAO;
import model.HumanBeing;
import model.HumanBeingRequestDTO;
import model.HumanBeingResponseDTO;

public class HumanBeingServiceImpl implements HumanBeingService {
    private final HumanBeingDAO humanBeingDAO;

    public HumanBeingServiceImpl(HumanBeingDAO userDAO) {
        this.humanBeingDAO = userDAO;
    }

    @Override
    public String info() {
        return humanBeingDAO.info();
    }

    @Override
    public void show() {
        humanBeingDAO.show();
    }

    @Override
    public String addElementToCollection(HumanBeingRequestDTO humanBeingRequestDTO) {
        return humanBeingDAO.addElementToCollection(humanBeingRequestDTO);
    }

    @Override
    public void updateById(Long id, HumanBeing humanBeing) {
        humanBeingDAO.updateById(id, humanBeing);
    }

    @Override
    public void removeById(Long id) {
        humanBeingDAO.removeById(id);
    }

    @Override
    public void clear() {
        humanBeingDAO.clear();
    }

    @Override
    public void save() {
        humanBeingDAO.save();
    }

    @Override
    public void executeScript(String fileName) {
        humanBeingDAO.executeScript(fileName);
    }

    @Override
    public void addIfMax(HumanBeing humanBeing) {
        humanBeingDAO.addIfMax(humanBeing);
    }

    @Override
    public void addIfMin(HumanBeing humanBeing) {
        humanBeingDAO.addIfMin(humanBeing);
    }

    @Override
    public void maxByImpactSpeed(HumanBeing humanBeing) {
        humanBeingDAO.maxByImpactSpeed(humanBeing);
    }

    @Override
    public void countByMood(HumanBeing humanBeing) {
        humanBeingDAO.countByMood(humanBeing);
    }

    @Override
    public void printAscending() {
        humanBeingDAO.printAscending();
    }
}
