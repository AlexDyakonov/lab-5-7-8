package server.db;

import server.exception.FileException;
import server.exception.ValidationException;
import server.mapper.HumanBeingMapper;
import server.model.HumanBeingModel;
import util.LANGUAGE;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

import static client.ui.ConsoleColors.error;
import static server.services.LoggerManager.setupLogger;
import static server.validation.Validation.validateFileRead;
import static util.Message.*;

/**
 * The type Data base provider.
 */
@Deprecated
public class DataBaseProvider {
    private static final Logger logger = Logger.getLogger(DataBaseProvider.class.getName());

    static {
        setupLogger(logger);
    }

    private final Set<HumanBeingModel> dataBase;
    private final LocalDateTime creationDate;
    private LANGUAGE language;

    /**
     * Instantiates a new Data base provider.
     *
     * @param fileName the file name
     */
    public DataBaseProvider(String fileName) {
        this.dataBase = loadDataBase(fileName);
        this.creationDate = LocalDateTime.now();
    }

    private static Set<HumanBeingModel> loadDataBase(String fileName) {
        logger.info(getLog("load_starting"));

        Set<HumanBeingModel> resultSet = new HashSet<>();

        logger.info(getLog("set_ready"));

        List<Long> idList = new ArrayList<>();

        String personString;
        try {
            validateFileRead(new File(fileName), LANGUAGE.RU);
        } catch (FileException e) {
            System.out.println(error(e.getMessage()));
            logger.severe(e.getMessage());
        }
        int num = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                try {
                    personString = reader.readLine();
                    HumanBeingModel person = HumanBeingMapper.fromStringToHumanBeingModel(personString);
                    if (idList.contains(person.getId())) {
                        logger.warning(getLog("same_id").replace("%id%", person.getId().toString()));
                        throw new FileException((getError("same_id", LANGUAGE.EN)).replace("%id%", person.getId().toString()));
                    }
                    idList.add(person.getId());
                    resultSet.add(person);
                    logger.fine(getLog("humanbeing_added_to_set").replace("%hb%", personString));
                    num++;
                } catch (ValidationException e) {
                    System.out.println(e.getMessage());
                    logger.warning(e.getMessage());
                } catch (FileException e) {
                    System.out.println(e.getMessage());
                    logger.severe(e.getMessage());
                }
            }
            logger.info((getLog("data_base_loaded")).replace("%size%", String.valueOf(num)));
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
        return resultSet;
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

    /**
     * Remove human from data base boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean removeHumanFromDataBase(Long id) {
        Set<HumanBeingModel> copy = new LinkedHashSet<>(dataBase);
        for (HumanBeingModel humanBeingModel : copy) {
            if (humanBeingModel.getId().equals(id)) {
                dataBase.remove(humanBeingModel);
                return true;
            }
        }
        return false;
    }

    /**
     * Save.
     *
     * @param fileName the file name
     */
    public void save(String fileName) {
        logger.info(getLog("start_saving"));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (HumanBeingModel model : dataBase) {
                writer.write(HumanBeingMapper.fromHumanBeingModelToStringLine(model));
                writer.write(System.lineSeparator());
            }
            System.out.println(getSuccessMessage("done", language));
            logger.info((getLog("data_base_saved")).replace("%size%", String.valueOf(dataBase.size())));
        } catch (IOException e) {
            System.out.println(getError("not_done", language));
            logger.severe(getLog("save_error"));
            logger.severe(e.getMessage());
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

    /**
     * Sets language.
     *
     * @param language the language
     */
    public void setLanguage(LANGUAGE language) {
        this.language = language;
    }
}