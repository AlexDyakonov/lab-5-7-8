package ru.home.app.server.services;

import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.authentication.ROLES;
import ru.home.app.server.dao.GuiHumanDao;
import ru.home.app.server.dao.GuiHumanDaoImpl;
import ru.home.app.server.model.User;
import ru.home.app.server.model.dto.HumanBeingRequestDTO;
import ru.home.app.server.model.dto.HumanBeingResponseDTO;
import ru.home.app.server.model.dto.HumanBeingResponseDTOwithUsers;
import ru.home.app.util.LANGUAGE;

import java.util.List;
import java.util.Set;

public class GuiHumanServiceImpl implements GuiHumanService {
    private final GuiHumanDao humanDao;
    private final CurrentUserManager userManager;
    private LANGUAGE language;

    public GuiHumanServiceImpl(CurrentUserManager userManager) {
        this.userManager = userManager;
        this.humanDao = new GuiHumanDaoImpl(userManager);
        humanDao.setLanguage(language);
    }

    @Override
    public HumanBeingResponseDTO getHumanById(Long id) {
        return humanDao.getHumanById(id);
    }

    @Override
    public List<HumanBeingResponseDTOwithUsers> getAllHuman() {
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

    @Override
    public List<User> getAllUsers() {
        return humanDao.getAllUsers();
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
    public void setUserManager(CurrentUserManager currentUserManager) {
        humanDao.setUserManager(currentUserManager);
    }

    @Override
    public void setRole(String username, ROLES role) {
        humanDao.setRole(username, role);
    }

    @Override
    public void setUserName(String userName) {
        humanDao.setUserName(userName);
    }

    @Override
    public void setLanguage(LANGUAGE language) {
        this.language = language;
        humanDao.setLanguage(language);
    }
}
