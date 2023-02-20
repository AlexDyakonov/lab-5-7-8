package services;

import dao.HumanBeingDAO;
import model.HumanBeing;
import model.HumanBeingRequestDTO;
import model.HumanBeingResponseDTO;
import model.Mood;

import java.util.List;
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
    public void updateById(String id, HumanBeing humanBeing) {
        humanBeingDAO.updateById(id, humanBeing);
    }

    @Override
    public void removeById(String id) {
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
    public HumanBeingResponseDTO maxByImpactSpeed(Float impactSpeed) {
        return humanBeingDAO.maxByImpactSpeed(impactSpeed);
    }

    @Override
    public void countByMood(Mood mood) {
        humanBeingDAO.countByMood(mood);
    }

    @Override
    public List<HumanBeing> printAscending() {
        return humanBeingDAO.printAscending();
    }

    @Override
    public int getSize() {
        return humanBeingDAO.getSize();
    }
}
