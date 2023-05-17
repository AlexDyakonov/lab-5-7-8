package ru.home.app.server.controller;

import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.authentication.ROLES;
import ru.home.app.server.model.User;
import ru.home.app.server.model.dto.HumanBeingRequestDTO;
import ru.home.app.server.model.dto.HumanBeingResponseDTO;
import ru.home.app.server.model.dto.HumanBeingResponseDTOwithUsers;
import ru.home.app.server.services.GuiHumanService;
import ru.home.app.server.services.GuiHumanServiceImpl;
import ru.home.app.server.validation.Validation;
import ru.home.app.util.LANGUAGE;

import java.util.List;
import java.util.Set;

import static ru.home.app.server.validation.Validation.validate;

public class GuiHumanControllerImpl implements GuiHumanController {
    private final GuiHumanService service;
    private final CurrentUserManager userManager;
    private LANGUAGE language;

    public GuiHumanControllerImpl(CurrentUserManager userManager) {
        this.userManager = userManager;
        this.service = new GuiHumanServiceImpl(userManager);
        service.setLanguage(language);
    }

    @Override
    public HumanBeingResponseDTO getHumanById(Long id) {
        validate(id, Validation::validateId, "Message"); // TODO сделать тут полноценную валидацию на все методы
        return service.getHumanById(id);
    }

    @Override
    public List<HumanBeingResponseDTOwithUsers> getAllHuman() {
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
    public String info() {
        return service.info();
    }

    @Override
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @Override
    public Set<String> getUserNameList() {
        return service.getUserNameList();
    }

    @Override
    public long userRegister(CurrentUserManager userManager, String password) {
        return service.userRegister(userManager, password);
    }

    @Override
    public boolean checkUserPassword(String username, String password) {
        validate(username, Validation::validateUserName, "Message");
        validate(password, Validation::validatePassword, "Message");
        return service.checkUserPassword(username, password);
    }

    @Override
    public void setUserManager(CurrentUserManager currentUserManager) {
        service.setUserManager(currentUserManager);
    }

    @Override
    public void setRole(String username, ROLES role) {
        service.setRole(username, role);
    }

    @Override
    public void setUserName(String userName) {
        service.setUserName(userName);
    }

    @Override
    public void setLanguage(LANGUAGE language) {
        this.language = language;
        service.setLanguage(language);
    }
}
