package DataBase;

import model.HumanBeing;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

public class DataBaseImpl implements DataBase {
    private final Set<HumanBeing> dataBase = new LinkedHashSet<>();
    private final java.time.LocalDateTime creationDate = LocalDateTime.now();

    public DataBaseImpl() {
    }

    public Set<HumanBeing> getDataBase() {
        return dataBase;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public void saveDBToCSV() {
        System.out.println("saved");
    }
}
