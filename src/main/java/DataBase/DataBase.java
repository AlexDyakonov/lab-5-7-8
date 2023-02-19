package DataBase;

import model.HumanBeing;

import java.time.LocalDateTime;
import java.util.Set;

public interface DataBase {
    Set<HumanBeing> getDataBase();
    LocalDateTime getCreationDate();
    void saveDBToCSV();
    void loadDataBase();
}
