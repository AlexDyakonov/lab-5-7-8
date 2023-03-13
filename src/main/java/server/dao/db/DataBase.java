package server.dao.db;

import server.model.HumanBeing;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * The interface Data base.
 */
public interface DataBase {
    /**
     * Gets data base.
     *
     * @return the data base
     */
    Set<HumanBeing> getDataBase();

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    LocalDateTime getCreationDate();

    /**
     * Save.
     */
    void save();
}
