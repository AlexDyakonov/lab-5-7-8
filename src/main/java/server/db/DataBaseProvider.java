package server.db;

import server.exception.ApplicationException;
import server.model.HumanBeingModel;

import java.io.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import static client.ui.ConsoleColors.GREEN_BRIGHT;
import static client.ui.ConsoleColors.RESET;

public class DataBaseProvider {
    private Set<HumanBeingModel> dataBase;
    private final LocalDateTime creationDate;

    public DataBaseProvider(String fileName) {
        this.dataBase = loadDataBase(fileName);
        this.creationDate = LocalDateTime.now();
    }

    private Long generateNextId() {
        return 1l;
    }

    public Long addHumanToDatabase(HumanBeingModel model) {
        Long id = generateNextId();
        model.setId(id);
        dataBase.add(model);
        return id;
    }

    private static Set<HumanBeingModel> loadDataBase(String fileName) {
        Set<HumanBeingModel> resultSet;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            resultSet = (LinkedHashSet<HumanBeingModel>) ois.readObject();
            System.out.println(GREEN_BRIGHT + "Успешно загружено " + resultSet.size() + " элементов." + RESET);
        } catch (IOException e) {
            throw new ApplicationException("load database from file *" + fileName + "+ error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public void save(String fileName) {
        try (ObjectOutputStream bos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            bos.writeObject(dataBase);
        } catch (IOException e) {
            throw new ApplicationException("save to file *" + fileName +"* error");
        }
    }

    public Set<HumanBeingModel> getDataBase() {
        return dataBase;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}