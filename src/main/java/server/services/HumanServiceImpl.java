package server.services;

import server.authentication.ROLES;
import server.authentication.UserManager;
import server.dao.HumanDao;
import server.dao.HumanDaoPostgresImpl;
import server.model.Mood;
import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;
import util.LANGUAGE;

import java.util.List;
import java.util.Set;

/**
 * The type Human service.
 */
public class HumanServiceImpl implements HumanService {
    private final HumanDao humanDao;
    private LANGUAGE language;

    /**
     * Instantiates a new Human service.
     */
    public HumanServiceImpl() {
        this.humanDao = new HumanDaoPostgresImpl();
        humanDao.setLanguage(language);
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
    public String info() {
        return humanDao.info();
    }

    @Deprecated
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

    @Override
    public void setLanguage(LANGUAGE language) {
        this.language = language;
        humanDao.setLanguage(language);
    }

    @Override
    public Set<String> getUserNameList() {
        return humanDao.getUserNameList();
    }

    @Override
    public void userRegister(String username, String password) {
        humanDao.userRegister(username, password);
    }

    @Override
    public boolean checkUserPassword(String username, String password) {
        return humanDao.checkUserPassword(username, password);
    }

    @Override
    public ROLES getUserRole(String userName) {
        return humanDao.getUserRole(userName);
    }

    @Override
    public void setUserName(String userName) {
        humanDao.setUserName(userName);
    }

    @Override
    public Long getUserId(String userName) {
        return humanDao.getUserId(userName);
    }

    @Override
    public void setUserManager(UserManager userManager) {
        humanDao.setUserManager(userManager);
    }
}
