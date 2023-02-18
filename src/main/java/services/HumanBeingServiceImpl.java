package services;

import dao.HumanBeingDAO;
import model.HumanBeing;
import model.HumanBeingRequestDTO;
import model.HumanBeingResponseDTO;

import java.util.Set;

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
    public Set<HumanBeing> show() {
        return humanBeingDAO.show();
    }

    @Override
    public HumanBeingResponseDTO addElementToCollection(HumanBeingRequestDTO humanBeingRequestDTO) {
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
    public Set<HumanBeing> clear() {
        humanBeingDAO.clear();
        return null;
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
