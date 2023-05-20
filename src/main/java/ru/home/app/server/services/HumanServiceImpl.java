package ru.home.app.server.services;

import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.authentication.ROLES;
import ru.home.app.server.dao.HumanDao;
import ru.home.app.server.dao.HumanDaoPostgresImpl;
import ru.home.app.server.model.Mood;
import ru.home.app.server.model.User;
import ru.home.app.server.model.dto.HumanBeingRequestDTO;
import ru.home.app.server.model.dto.HumanBeingResponseDTO;
import ru.home.app.server.model.dto.HumanBeingResponseDTOwithUsers;
import ru.home.app.util.LANGUAGE;

import java.util.List;
import java.util.Set;

public class HumanServiceImpl implements HumanService {
    private final HumanDao humanDao;
    private final CurrentUserManager userManager;
    private LANGUAGE language;

    public HumanServiceImpl(CurrentUserManager userManager) {
        this.userManager = userManager;
        this.humanDao = new HumanDaoPostgresImpl(userManager);
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
    public List<HumanBeingResponseDTOwithUsers> getAllHumanWithUsers() {
        return humanDao.getAllHumanWithUsers();
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

    @Override
    public void clear() {
        humanDao.clear();
    }

    @Override
    public void clearAll() {
        humanDao.clearAll();
    }

    @Deprecated
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
    public long userRegister(CurrentUserManager userManager, String password) {
        return humanDao.userRegister(userManager, password);
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
    public void setUserManager(CurrentUserManager currentUserManager) {
        humanDao.setUserManager(currentUserManager);
    }

    @Override
    public void setRole(String username, ROLES role) {
        humanDao.setRole(username, role);
    }

    @Override
    public List<User> getAllUsers() {
        return humanDao.getAllUsers();
    }
}
