package server.db;

import server.exception.FileException;
import server.exception.ValidationException;
import server.mapper.HumanBeingMapper;
import server.model.HumanBeingModel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static client.ui.ConsoleColors.*;
import static server.validation.Validation.validateFileRead;

/**
 * The type Data base provider.
 */
public class DataBaseProvider {
    private final Set<HumanBeingModel> dataBase;
    private final LocalDateTime creationDate;

    /**
     * Instantiates a new Data base provider.
     *
     * @param fileName the file name
     */
    public DataBaseProvider(String fileName) {
        this.dataBase = loadDataBase(fileName);
        this.creationDate = LocalDateTime.now();
    }

    private synchronized Long generateNextId() {
        return dataBase.stream().mapToLong(HumanBeingModel::getId).max().isPresent()
                ? dataBase.stream().mapToLong(HumanBeingModel::getId).max().getAsLong() + 1
                : 1;
    }

    /**
     * Add human to database long.
     *
     * @param model the model
     * @return the long
     */
    public Long addHumanToDatabase(HumanBeingModel model) {
        Long id = generateNextId();
        model.setId(id);
        dataBase.add(model);
        return id;
    }

    private static Set<HumanBeingModel> loadDataBase(String fileName) {
        Set<HumanBeingModel> resultSet = new HashSet<>();
        List<Long> idList = new ArrayList<>();

        String personString;
        try {
            validateFileRead(new File(fileName));
        } catch (FileException e) {
            System.out.println(error(e.getMessage()));
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                try {
                    personString = reader.readLine();
                    HumanBeingModel person = HumanBeingMapper.fromStringToHumanBeingModel(personString);
                    if (idList.contains(person.getId())) {
                        throw new FileException(error("Найдено два пользователя с одинаковым id. Загружен первый из них."));
                    }
                    idList.add(person.getId());
                    resultSet.add(person);
                } catch (ValidationException | FileException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(GREEN_BRIGHT + "Успешно загружено " + resultSet.size() + " элементов." + RESET);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return resultSet;
    }

    /**
     * Save.
     *
     * @param fileName the file name
     */
    public void save(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
//            writer.write("id, name, coordinates, LocalDateTime, realHero, hasToothpick, ImpactSpeed, SoundtrackName, weaponType, Mood, Car");
            for (HumanBeingModel model : dataBase) {
                writer.write(HumanBeingMapper.fromHumanBeingModelToStringLine(model));
                writer.write(System.lineSeparator());
            }
            System.out.println(success("Было сохранено " + dataBase.size() + " элементов."));
        } catch (IOException e) {
            System.out.println("Ошибка сохранения в *" + fileName + "*");
        }
    }

    /**
     * Gets data base.
     *
     * @return the data base
     */
    public Set<HumanBeingModel> getDataBase() {
        return dataBase;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public FileTime getFileCreationDate(String fileName) {
        try {
            return (FileTime) Files.getAttribute((new File(fileName)).toPath(), "creationTime");
        } catch (IOException e) {
            return null;
        }
    }
}