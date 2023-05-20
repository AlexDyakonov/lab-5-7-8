package ru.home.app.server.dao;

import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.authentication.ROLES;
import ru.home.app.server.model.Mood;
import ru.home.app.server.model.User;
import ru.home.app.server.model.dto.HumanBeingRequestDTO;
import ru.home.app.server.model.dto.HumanBeingResponseDTO;
import ru.home.app.server.model.dto.HumanBeingResponseDTOwithUsers;
import ru.home.app.util.LANGUAGE;

import java.util.List;
import java.util.Set;

public interface HumanDao {
    HumanBeingResponseDTO getHumanById(Long id);

    List<HumanBeingResponseDTO> getAllHuman();

    List<HumanBeingResponseDTOwithUsers> getAllHumanWithUsers();

    Long createHuman(HumanBeingRequestDTO human);

    void deleteHumanById(Long id);

    HumanBeingResponseDTO updateHuman(HumanBeingRequestDTO newHuman, Long id);

    String info();

    void clear();

    @Deprecated
    void save(String fileName);

    HumanBeingResponseDTO max_by_impact_speed();

    List<HumanBeingResponseDTO> print_ascending();

    int countByMood(Mood mood);

    boolean isImpactSpeedMax(HumanBeingRequestDTO dto);

    boolean isImpactSpeedMin(HumanBeingRequestDTO dto);

    Long addIfMax(HumanBeingRequestDTO request);

    Long addIfMin(HumanBeingRequestDTO request);

    void setLanguage(LANGUAGE language);

    Set<String> getUserNameList();

    long userRegister(CurrentUserManager userManager, String password);

    boolean checkUserPassword(String username, String password);

    ROLES getUserRole(String userName);

    void setUserName(String userName);

    Long getUserId(String userName);

    void setUserManager(CurrentUserManager currentUserManager);

    void clearAll();

    void setRole(String username, ROLES role);

    List<User> getAllUsers();
}
