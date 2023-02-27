package DataBase;

import model.HumanBeing;
import fileLogic.FileManager;
import fileLogic.FileManagerImpl;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import static ui.ConsoleColors.*;

public class DataBaseImpl implements DataBase {
    private Set<HumanBeing> dataBase;
    private final java.time.LocalDateTime creationDate = LocalDateTime.now();
    private final FileManager fileManager = new FileManagerImpl();

    public DataBaseImpl() {
        try {
            dataBase = new LinkedHashSet<>(fileManager.load("database.csv"));
            System.out.println(GREEN_BRIGHT + "Успешно загружено " + dataBase.size() + " элементов." + RESET);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            dataBase = new LinkedHashSet<>();
        }

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
    @Override
    public void saveDBToCSV() {
        fileManager.save(dataBase, "bebra");
    }
}
