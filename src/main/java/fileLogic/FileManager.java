package fileLogic;

import model.HumanBeing;
import model.HumanBeingResponseDTO;

import java.util.Set;

/**
 * The interface File manager.
 */
public interface FileManager {
    /**
     * Save.
     *
     * @param humanBeingSet the human being set
     * @param fileName      the file name
     */
    void save(Set<HumanBeing> humanBeingSet, String fileName);

    /**
     * Load set.
     *
     * @param path the path
     * @return the set
     */
    Set<HumanBeing> load(String path);
}
