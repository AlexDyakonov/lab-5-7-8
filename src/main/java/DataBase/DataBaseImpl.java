package DataBase;

import model.HumanBeing;
import utility.FileManager;
import utility.FileManagerImpl;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

public class DataBaseImpl implements DataBase {
    private final Set<HumanBeing> dataBase = new LinkedHashSet<>();
    private final java.time.LocalDateTime creationDate = LocalDateTime.now();
    private final FileManager fileManager = new FileManagerImpl();

    public DataBaseImpl() {
    }
    public void loadDataBase(){
        fileManager.load("database.csv");
    }

    public Set<HumanBeing> getDataBase() {
        return dataBase;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    private String toCSV(){
        StringBuilder outputString = new StringBuilder();
        for (HumanBeing humanBeing : dataBase){
            outputString.append(humanBeing.toCSV()).append("\n");
        }
        return outputString.toString();
    }
//не работает такое
    @Override
    public void saveDBToCSV() {
        fileManager.save(dataBase, "bebra");
    }
}
