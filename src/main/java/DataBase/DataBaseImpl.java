package DataBase;

import exception.FileException;
import model.HumanBeing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
//не работает такое
    @Override
    public void saveDBToCSV() {
        try {
            File csvFile = new File("people.csv");
            PrintWriter out = new PrintWriter(csvFile);

            for(HumanBeing humanBeing : dataBase){
                out.println(humanBeing.toString());
            }
        } catch (FileNotFoundException e) {
            throw new FileException("Что-то не удалось с файлом");
        }
        System.out.println("saved");
    }
}
