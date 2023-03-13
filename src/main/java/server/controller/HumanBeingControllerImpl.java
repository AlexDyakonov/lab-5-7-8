package server.controller;

import server.model.HumanBeing;
import server.model.HumanBeingRequestDTO;
import server.model.HumanBeingResponseDTO;
import server.model.Mood;
import server.services.HumanBeingService;
import server.services.HumanBeingServiceImpl;

import java.util.List;
import java.util.Set;

/**
 * The type Human being server.server.controller.
 */
//@Deprecated
public class HumanBeingControllerImpl implements HumanBeingController {
    private final HumanBeingService humanBeingService;

    /**
     * Instantiates a new Human being server.server.controller.
     */
    public HumanBeingControllerImpl() {
        this.humanBeingService = new HumanBeingServiceImpl();
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
