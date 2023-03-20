package server.services;

import client.ui.MenuConstants;
import server.dao.HumanDao;
import server.dao.HumanDaoImpl;
import server.model.Mood;
import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;

import java.util.List;

/**
 * The type Human service.
 */
public class HumanServiceImpl implements HumanService {

    private final HumanDao humanDao;

    /**
     * Instantiates a new Human service.
     *
     * @param fileName the file name
     */
    public HumanServiceImpl(String fileName) {
        this.humanDao = new HumanDaoImpl(fileName);
    }

    @Override
    public HumanBeingResponseDTO getHumanById(Long id) {
        return humanDao.getHumanById(id);
    }

    @Override
    public List<HumanBeingResponseDTO> getAllHuman() {
        return humanDao.getAllHuman();
    }

    @Override
    public Long createHuman(HumanBeingRequestDTO human) {
        return humanDao.createHuman(human);
    }

    @Override
    public void deleteHumanById(Long id) {
        humanDao.deleteHumanById(id);
    }

    @Override
    public HumanBeingResponseDTO updateHuman(HumanBeingRequestDTO newHuman, Long id) {
        return humanDao.updateHuman(newHuman, id);
    }

    @Override
    public String help() {
        return MenuConstants.HELP;
    }

    @Override
    public String info() {
        return humanDao.info();
    }

    @Override
    public void clear() {
        humanDao.clear();
    }

    @Override
    public void save(String fileName) {
        humanDao.save(fileName);
    }

    @Override
    public HumanBeingResponseDTO max_by_impact_speed() {
        return humanDao.max_by_impact_speed();
    }

    @Override
    public List<HumanBeingResponseDTO> print_ascending() {
        return humanDao.print_ascending();
    }

    @Override
    public Long addIfMax(HumanBeingRequestDTO request) {
        return humanDao.addIfMax(request);
    }

    @Override
    public Long addIfMin(HumanBeingRequestDTO request) {
        return humanDao.addIfMin(request);
    }

    @Override
    public int countByMood(Mood mood) {
        return humanDao.countByMood(mood);
    }

    @Override
    public boolean isImpactSpeedMax(HumanBeingRequestDTO dto) {
        return humanDao.isImpactSpeedMax(dto);
    }

    @Override
    public boolean isImpactSpeedMin(HumanBeingRequestDTO dto) {
        return humanDao.isImpactSpeedMin(dto);
    }
}
