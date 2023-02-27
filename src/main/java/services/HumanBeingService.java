package services;

import model.HumanBeing;
import model.HumanBeingRequestDTO;
import model.HumanBeingResponseDTO;
import model.Mood;

import java.util.List;
import java.util.Set;

/**
 * The interface Human being service.
 */
public interface HumanBeingService {
    /**
     * Info string.
     *
     * @return the string
     */
    String info();

    /**
     * Show set.
     *
     * @return the set
     */
    Set<HumanBeing> show();

    /**
     * Add element to collection human being response dto.
     *
     * @param humanBeingRequestDTO the human being request dto
     * @return the human being response dto
     */
    HumanBeingResponseDTO addElementToCollection(HumanBeingRequestDTO humanBeingRequestDTO);

    /**
     * Find by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean findById(String id);

    /**
     * Update by id human being response dto.
     *
     * @param id                   the id
     * @param humanBeingRequestDTO the human being request dto
     * @return the human being response dto
     */
    HumanBeingResponseDTO updateById(String id, HumanBeingRequestDTO humanBeingRequestDTO);

    /**
     * Remove by id.
     *
     * @param id the id
     */
    void removeById(String id);

    /**
     * Clear set.
     *
     * @return the set
     */
    Set<HumanBeing> clear();

    /**
     * Save.
     */
    void save();

    /**
     * Execute script.
     *
     * @param fileName the file name
     */
    void executeScript(String fileName);

    /**
     * Add if max.
     *
     * @param humanBeingRequestDTO the human being request dto
     */
    void addIfMax(HumanBeingRequestDTO humanBeingRequestDTO);

    /**
     * Add if min.
     *
     * @param humanBeingRequestDTO the human being request dto
     */
    void addIfMin(HumanBeingRequestDTO humanBeingRequestDTO);

    /**
     * Max by impact speed human being response dto.
     *
     * @return the human being response dto
     */
    HumanBeingResponseDTO maxByImpactSpeed();

    /**
     * Count by mood.
     *
     * @param mood the mood
     */
    void countByMood(Mood mood);

    /**
     * Print ascending list.
     *
     * @return the list
     */
    List<HumanBeing> printAscending();

    /**
     * Gets size.
     *
     * @return the size
     */
    int getSize();
}
