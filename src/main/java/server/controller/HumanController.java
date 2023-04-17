package server.controller;

import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;
import util.LANGUAGE;

import java.util.List;

/**
 * The interface Human controller.
 */
public interface HumanController {

    // core commands

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

    // additional commands

    /**
     * Help string.
     *
     * @return the string
     */
    String help();

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
    void save(String fileName, LANGUAGE language);

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

    // additional composite commands

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
    int countByMood(String mood);

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

    LANGUAGE getLanguage();

    void setLanguage(LANGUAGE language);
}
