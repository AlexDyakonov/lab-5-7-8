package server.services;

import client.ui.MenuConstants;
import server.dao.HumanDao;
import server.dao.HumanDaoImpl;
import server.model.Mood;
import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;

import java.util.List;

public class HumanServiceImpl implements HumanService {

    private final HumanDao repo;

    public HumanServiceImpl(String fileName) {
        this.repo = new HumanDaoImpl(fileName);
    }

    @Override
    public HumanBeingResponseDTO getHumanById(Long id) {
        return repo.getHumanById(id);
    }

    @Override
    public List<HumanBeingResponseDTO> getAllHuman() {
        return repo.getAllHuman();
    }

    @Override
    public Long createHuman(HumanBeingRequestDTO human) {
        return repo.createHuman(human);
    }

    @Override
    public void deleteHumanById(Long id) {
        repo.deleteHumanById(id);
    }

    @Override
    public HumanBeingResponseDTO updateHuman(HumanBeingRequestDTO newHuman, Long id) {
        return repo.updateHuman(newHuman, id);
    }

    @Override
    public String help() {
        return MenuConstants.HELP;
    }

    @Override
    public String info() {
        return repo.info();
    }

    @Override
    public void clear() {
        repo.clear();
    }

    @Override
    public void save(String fileName) {
        repo.save(fileName);
    }

    @Override
    public HumanBeingResponseDTO max_by_impact_speed() {
        return repo.max_by_impact_speed();
    }

    @Override
    public List<HumanBeingResponseDTO> print_ascending() {
        return repo.print_ascending();
    }

    @Override
    public Long addIfMax(HumanBeingRequestDTO request) {
        return repo.addIfMax(request);
    }

    @Override
    public Long addIfMin(HumanBeingRequestDTO request) {
        return repo.addIfMin(request);
    }

    @Override
    public int countByMood(Mood mood) {
        return repo.countByMood(mood);
    }

    @Override
    public boolean isImpactSpeedMax(HumanBeingRequestDTO dto) {
        return repo.isImpactSpeedMax(dto);
    }

    @Override
    public boolean isImpactSpeedMin(HumanBeingRequestDTO dto) {
        return repo.isImpactSpeedMin(dto);
    }
}
