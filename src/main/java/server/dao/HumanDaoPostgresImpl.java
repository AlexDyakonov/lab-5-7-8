package server.dao;

import server.db.SQLDataBaseProvider;
import server.exception.ApplicationException;
import server.model.Car;
import server.model.Coordinates;
import server.model.Mood;
import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;
import server.sql.SQLConnection;
import util.LANGUAGE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static server.mapper.HumanBeingMapper.fromRequestToResponse;
import static server.services.LoggerManager.setupLogger;
import static util.Message.*;

public class HumanDaoPostgresImpl implements HumanDao {
    private static final Logger logger = Logger.getLogger(HumanDaoPostgresImpl.class.getName());
    private final SQLConnection sqlConnection = new SQLConnection();
    private final SQLDataBaseProvider source = new SQLDataBaseProvider(sqlConnection);
    private LANGUAGE language;

    public HumanDaoPostgresImpl() {
        setupLogger(logger);
    }

    @Override
    public HumanBeingResponseDTO getHumanById(Long id) {
        return source.getHumanBeingById(id);
    }

    @Override
    public List<HumanBeingResponseDTO> getAllHuman() {
        return source.getDataSet().stream().toList();
    }

    @Override
    public Long createHuman(HumanBeingRequestDTO human) {
        Long id = addHumanBeingToDB(human);
        source.getDataSet().add(fromRequestToResponse(human));
        return id;
    }

    @Override
    public void deleteHumanById(Long id) {
        if (source.findHumanById(id)) {
            try {
                String query = "DELETE FROM humanbeing WHERE humanbeing_id = ?";
                PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
                preparedStatement.setInt(1, id.intValue());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new ApplicationException("Не удалось добавить юзера");
                }
                preparedStatement.close();
                System.out.println(getSuccessMessage("done", language));
                logger.info((getLog("user_deleted")).replace("%id%", id.toString()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println((getWarning("user_not_found", language)).replace("%id%", id.toString()));
            logger.warning((getWarning("user_not_found", LANGUAGE.EN)).replace("%id%", id.toString()));
        }
    }

    @Override
    public HumanBeingResponseDTO updateHuman(HumanBeingRequestDTO request, Long id) {
        try {
            logger.info(getLog("update_starting").replace("%id%", id.toString()));
            String query = "UPDATE humanbeing SET name = ?, coordinates_id = ?, " +
                    "real_hero = ?, has_toothpick = ?, impact_speed = ?, " +
                    "soundtrack = ?,mood = ?, weapon_type= ?, car_id = ? WHERE humanbeing_id = ? ;";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);

            preparedStatement.setString(1, request.getName());
            preparedStatement.setInt(2, addCoordinatesToDB(request.getCoordinates()));
            preparedStatement.setBoolean(3, request.getRealHero());
            preparedStatement.setBoolean(4, request.getHasToothpick());
            preparedStatement.setFloat(5, request.getImpactSpeed());
            preparedStatement.setString(6, request.getSoundtrackName());
            preparedStatement.setString(7, request.getMood().toString());
            preparedStatement.setString(8, request.getWeaponType().toString());
            preparedStatement.setInt(9, addCarToDB(request.getCar()));
            preparedStatement.setInt(10, id.intValue());


            int affectedRows = preparedStatement.executeUpdate();
            preparedStatement.close();

            if (affectedRows == 0) {
                System.out.println(getError("not_done", language));
                throw new ApplicationException(getLog("update_error").replace("%id%", id.toString()));
            }


            source.updateDataSet();
            System.out.println(getSuccessMessage("done", language));
            logger.info((getLog("update_finished")).replace("%id%", id.toString()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public String info() {
        String answer = (getMessage("info_about_dataset", language));
        answer = answer.replace("%class%", source.getDataSet().getClass().getTypeName().split("\\.")[2]);
        answer = answer.replace("%date%", source.getCreationDate().toString());
        answer = answer.replace("%size%", String.valueOf(source.getDataSet().size()));
        logger.info(getLog("info_got"));
        return answer;
    }

    @Override
    public void clear() {
        int elemsBefore = source.getDataSet().size();
        try {
            String query = "TRUNCATE humanbeing CASCADE;";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);

            int affectedRows = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (affectedRows == 0) {
                throw new ApplicationException(getLog("not_cleared"));
            }

            source.updateDataSet();

            System.out.println(getSuccessMessage("done", language));
            logger.info(getLog("cleared").replace("%num%", String.valueOf(elemsBefore)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    @Override
    public void save(String fileName) {
    }

    @Override
    public HumanBeingResponseDTO max_by_impact_speed() {
        HumanBeingResponseDTO model = new HumanBeingResponseDTO();
        float max = 0;
        for (HumanBeingResponseDTO m : source.getDataSet()) {
            if (m.getImpactSpeed() > max) {
                max = m.getImpactSpeed();
                model = m;
            }
        }
        return model;
    }

    @Override
    public List<HumanBeingResponseDTO> print_ascending() {
        List<HumanBeingResponseDTO> list = new java.util.ArrayList<>(source.getDataSet().stream().sorted().toList());
        Collections.reverse(list);
        return list;
    }

    @Override
    public Long addIfMax(HumanBeingRequestDTO request) {
        if (isImpactSpeedMax(request)) {
            return createHuman(request);
        } else {
            return -1L;
        }
    }

    @Override
    public Long addIfMin(HumanBeingRequestDTO request) {
        if (isImpactSpeedMin(request)) {
            return createHuman(request);
        } else {
            return -1L;
        }
    }

    @Override
    public int countByMood(Mood mood) {
        return (int) source.getDataSet().stream().filter(p -> p.getMood().equals(mood)).count();
    }

    @Override
    public boolean isImpactSpeedMax(HumanBeingRequestDTO dto) {
        float max = source.getDataSet().stream().max(Comparator.comparing(HumanBeingResponseDTO::getImpactSpeed))
                .orElse(null).getImpactSpeed();
        if (dto.getImpactSpeed() > max) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isImpactSpeedMin(HumanBeingRequestDTO dto) {
        float min = source.getDataSet().stream().min(Comparator.comparing(HumanBeingResponseDTO::getImpactSpeed))
                .orElse(null).getImpactSpeed();
        if (dto.getImpactSpeed() < min) {
            return true;
        }
        return false;
    }

    @Override
    public void setLanguage(LANGUAGE language) {
        this.language = language;
    }

    public int addCoordinatesToDB(Coordinates coordinates) {
        int coordinatesId = 0;
        try {
            String query = "INSERT INTO coordinates VALUES (DEFAULT, ?, ?) RETURNING coordinates_id";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, coordinates.getX());
            preparedStatement.setDouble(2, coordinates.getY());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                coordinatesId = resultSet.getInt("coordinates_id");
            }
            preparedStatement.close();
            return coordinatesId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int addCarToDB(Car car) {
        int carId = 0;
        try {
            if (car == null) {
                return 1;
            }
            String query = "INSERT INTO cars VALUES (DEFAULT, ?, ?) RETURNING car_id";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, car.getName());
            preparedStatement.setBoolean(2, car.isCool());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                carId = resultSet.getInt("car_id");
            }
            preparedStatement.close();
            return carId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long addHumanBeingToDB(HumanBeingRequestDTO request) {
        long id = 0L;
        try {
            String query = "INSERT INTO humanbeing values (DEFAULT, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?, ?) RETURNING humanbeing_id";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, request.getName());
            preparedStatement.setInt(2, addCoordinatesToDB(request.getCoordinates()));
            preparedStatement.setBoolean(3, request.getRealHero());
            preparedStatement.setBoolean(4, request.getHasToothpick());
            preparedStatement.setFloat(5, request.getImpactSpeed());
            preparedStatement.setString(6, request.getSoundtrackName());
            preparedStatement.setString(7, request.getMood().toString());
            preparedStatement.setString(8, request.getWeaponType().toString());
            preparedStatement.setInt(9, addCarToDB(request.getCar()));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("humanbeing_id");
            }
            preparedStatement.close();
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<String> getUserNameList() {
        return source.getUserNameList();
    }

    @Override
    public void userRegister(String username, String password) {
        source.userRegister(username, password);
    }

    @Override
    public boolean checkUserPassword(String username, String password) {
        return source.checkUserPassword(username, password);
    }
}
