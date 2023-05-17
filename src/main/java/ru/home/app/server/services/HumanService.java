package ru.home.app.server.services;

import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.authentication.ROLES;
import ru.home.app.server.model.Mood;
import ru.home.app.server.model.User;
import ru.home.app.server.model.dto.HumanBeingRequestDTO;
import ru.home.app.server.model.dto.HumanBeingResponseDTO;
import ru.home.app.util.LANGUAGE;

import java.util.List;
import java.util.Set;

/**
 * The interface Human service.
 */
public interface HumanService {
    /**
     * Gets human by id.
     *
     * @param id the id
     * @return the human by id
     */
    HumanBeingResponseDTO getHumanById(Long id);

    /**
     * Gets all human.
     *
     * @return the all human
     */
    List<HumanBeingResponseDTO> getAllHuman();

    /**
     * Create human long.
     *
     * @param human the human
     * @return the long
     */
    Long createHuman(HumanBeingRequestDTO human);

    /**
     * Delete human by id.
     *
     * @param id the id
     */
    void deleteHumanById(Long id);

    /**
     * Update human human being response dto.
     *
     * @param newHuman the new human
     * @param id       the id
     * @return the human being response dto
     */
    HumanBeingResponseDTO updateHuman(HumanBeingRequestDTO newHuman, Long id);

    /**
     * Info string.
     *
     * @return the string
     */
    String info();

    /**
     * Clear.
     */
    void clear();

    /**
     * Save.
     *
     * @param fileName the file name
     */
    @Deprecated
    void save(String fileName);

    /**
     * Max by impact speed human being response dto.
     *
     * @return the human being response dto
     */
    HumanBeingResponseDTO max_by_impact_speed();

    /**
     * Print ascending list.
     *
     * @return the list
     */
    List<HumanBeingResponseDTO> print_ascending();

    /**
     * Add if max long.
     *
     * @param request the request
     * @return the long
     */
    Long addIfMax(HumanBeingRequestDTO request);

    /**
     * Add if min long.
     *
     * @param request the request
     * @return the long
     */
    Long addIfMin(HumanBeingRequestDTO request);

    /**
     * Count by mood int.
     *
     * @param mood the mood
     * @return the int
     */
    int countByMood(Mood mood);

    /**
     * Is impact speed max boolean.
     *
     * @param dto the dto
     * @return the boolean
     */
    boolean isImpactSpeedMax(HumanBeingRequestDTO dto);

    /**
     * Is impact speed min boolean.
     *
     * @param dto the dto
     * @return the boolean
     */
    boolean isImpactSpeedMin(HumanBeingRequestDTO dto);

    /**
     * Sets language.
     *
     * @param language the language
     */
    void setLanguage(LANGUAGE language);

    Set<String> getUserNameList();

    void userRegister(String username, String password);

    boolean checkUserPassword(String username, String password);

    ROLES getUserRole(String userName);

    void setUserName(String userName);

    Long getUserId(String userName);

    void setUserManager(CurrentUserManager currentUserManager);

    void clearAll();

    void setRole(String username, ROLES role);

    List<User> getAllUsers();
}