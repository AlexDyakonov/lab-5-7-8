package server.controller;

import server.model.Mood;
import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;
import server.services.HumanService;
import server.services.HumanServiceImpl;

import java.util.List;

public class HumanControllerImpl implements HumanController {

    private final HumanService service;

    public HumanControllerImpl(String fileName) {
        this.service = new HumanServiceImpl(fileName);
    }

    @Override
    public HumanBeingResponseDTO getHumanById(Long id) {
        return service.getHumanById(id);
    }

    @Override
    public List<HumanBeingResponseDTO> getAllHuman() {
        return service.getAllHuman();
    }

    @Override
    public Long createHuman(HumanBeingRequestDTO human) {
        return service.createHuman(human);
    }

    @Override
    public void deleteHumanById(Long id) {
        service.deleteHumanById(id);
    }

    @Override
    public HumanBeingResponseDTO updateHuman(HumanBeingRequestDTO newHuman, Long id) {
        return service.updateHuman(newHuman, id);
    }

    @Override
    public String help() {
        return service.help();
    }

    @Override
    public String info() {
        return service.info();
    }

    @Override
    public void clear() {
        service.clear();
    }

    @Override
    public void save(String fileName) {
        service.save(fileName);
    }

    @Override
    public HumanBeingResponseDTO max_by_impact_speed() {
        return service.max_by_impact_speed();
    }

    @Override
    public List<HumanBeingResponseDTO> print_ascending() {
        return service.print_ascending();
    }

    @Override
    public Long addIfMax(HumanBeingRequestDTO request) {
        return service.addIfMax(request);
    }

    @Override
    public Long addIfMin(HumanBeingRequestDTO request) {
        return service.addIfMin(request);
    }

    @Override
    public int countByMood(Mood mood) {
        return service.countByMood(mood);
    }
}
