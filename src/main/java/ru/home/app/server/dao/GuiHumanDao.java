package ru.home.app.server.dao;

import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.authentication.ROLES;
import ru.home.app.server.model.User;
import ru.home.app.server.model.dto.HumanBeingRequestDTO;
import ru.home.app.server.model.dto.HumanBeingResponseDTO;
import ru.home.app.util.LANGUAGE;

import java.util.List;
import java.util.Set;

public interface GuiHumanDao {
    HumanBeingResponseDTO getHumanById(Long id);

    List<HumanBeingResponseDTO> getAllHuman();

    Long createHuman(HumanBeingRequestDTO human);

    void deleteHumanById(Long id);

    HumanBeingResponseDTO updateHuman(HumanBeingRequestDTO newHuman, Long id);

    String info();

    List<User> getAllUsers();

    Set<String> getUserNameList();

    void userRegister(String username, String password);

    boolean checkUserPassword(String username, String password);

    void setUserManager(CurrentUserManager currentUserManager);

    void setRole(String username, ROLES role);

    void setUserName(String userName);

    void setLanguage(LANGUAGE language);
}
