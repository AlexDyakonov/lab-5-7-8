package ru.home.app.server.db;

import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.authentication.ROLES;
import ru.home.app.server.exception.ApplicationException;
import ru.home.app.server.model.Car;
import ru.home.app.server.model.Coordinates;
import ru.home.app.server.model.Mood;
import ru.home.app.server.model.WeaponType;
import ru.home.app.server.model.dto.HumanBeingResponseDTO;
import ru.home.app.server.model.dto.HumanBeingResponseDTOwithUsers;
import ru.home.app.server.sql.SQLConnection;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

import static ru.home.app.server.services.LoggerManager.setupLogger;
import static ru.home.app.util.Message.getLog;
import static ru.home.app.util.Parser.convertTimeStampToZoned;


public class SQLDataBaseProvider {
    private static final Logger logger = Logger.getLogger(SQLDataBaseProvider.class.getName());

    static {
        setupLogger(logger);
    }

    private final SQLConnection sqlConnection;
    private final LocalDateTime creationDate;
    private final String pepper = "*63&^mVLC(#";
    private CurrentUserManager userManager;
    private Set<HumanBeingResponseDTO> dataSet;


    public SQLDataBaseProvider(SQLConnection sqlConnection, CurrentUserManager userManager) {
        this.sqlConnection = sqlConnection;
        this.dataSet = new HashSet<>(); //changed to save resources
        this.creationDate = LocalDateTime.now();
        this.userManager = userManager;
    }

    private static String saltBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            stringBuilder.append((char) new Random().nextInt(33, 126));
        }
        logger.info(getLog("salt_built"));
        return stringBuilder.toString();
    }

    private static String sha256encoding(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = md.digest(pass.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            logger.info(getLog("encoding_success"));
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.severe(getLog("encoding_error"));
            logger.severe(e.getMessage());
            throw new ApplicationException(getLog("encoding_error"));
        }
    }

    private Set<HumanBeingResponseDTO> loadDataBase() {
        logger.info(getLog("load_starting"));

        Set<HumanBeingResponseDTO> dbSet = new HashSet<>();

        logger.info(getLog("set_ready"));

        try {
            String query = "SELECT humanbeing_id FROM humanbeing";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dbSet.add(getHumanBeingById(resultSet.getLong("humanbeing_id")));
            }
            preparedStatement.close();
            logger.info(getLog("hs_filled"));
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
        return dbSet;
    }

    public Set<HumanBeingResponseDTO> updateDataSet() {
        dataSet = loadDataBase();
        return dataSet;
    }

    public List<HumanBeingResponseDTOwithUsers> getAllHumanWithUser() {
        List<HumanBeingResponseDTOwithUsers> resultList = new ArrayList<>();

        for (HumanBeingResponseDTO humanBeingResponseDTO : loadDataBase()) {
            resultList.add(getHumanBeingById(humanBeingResponseDTO.getId()));
        }

        return resultList;
    }


    public Coordinates getCoordinates(int id) {
        Coordinates coordinates = new Coordinates();
        try {
            String query = "SELECT x, y FROM coordinates WHERE coordinates_id = ?";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                coordinates.setX(resultSet.getInt("x"));
                coordinates.setY(resultSet.getDouble("y"));
            }
            preparedStatement.close();
            return coordinates;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private String getCarName(int id) {
        String carName = null;
        if (id == 0) {
            return null;
        }
        try {
            String query = "SELECT name FROM cars WHERE car_id = ?";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                carName = resultSet.getString("name");
            }
            preparedStatement.close();
            return carName;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private boolean getCarCool(int id) {
        boolean answer = false;
        try {
            String query = "SELECT cool FROM cars WHERE car_id = ?";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                answer = resultSet.getBoolean("cool");
            }
            preparedStatement.close();
            return answer;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Car getCar(int id) {
        if (id == 1) {
            return null;
        }
        return new Car(getCarName(id), getCarCool(id));
    }

    public boolean findHumanById(Long id) {
        Set<Long> idSet = new HashSet<>();
        try {
            String query = "SELECT humanbeing_id FROM humanbeing";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idSet.add(resultSet.getLong("humanbeing_id"));
            }
            if (idSet.contains(id)) {
                return true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean removeHumanById(Long id) {
        try {
            String query = "DELETE FROM humantouser WHERE humanbeing_id = ?";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new ApplicationException("Не удалось добавить юзера");
            }
            preparedStatement.close();

            String query1 = "DELETE FROM humanbeing WHERE humanbeing_id = ?";
            PreparedStatement preparedStatement1 = sqlConnection.getConnection().prepareStatement(query1);
            preparedStatement1.setInt(1, id.intValue());

            int affectedRows1 = preparedStatement1.executeUpdate();
            if (affectedRows1 == 0) {
                return false;
            }
            preparedStatement1.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HumanBeingResponseDTOwithUsers getHumanBeingById(Long id) {
        if (!findHumanById(id)) {
            return null;
        }
        try {
            HumanBeingResponseDTOwithUsers response = new HumanBeingResponseDTOwithUsers();
            String query =
                    "SELECT  h.humanbeing_id, h.name, h.coordinates_id, h.creation_time, h.real_hero, h.has_toothpick, " +
                            "h.impact_speed, h.soundtrack, h.mood, h.weapon_type, h.car_id, u.user_name FROM humanbeing h " +
                            "JOIN humantouser hu ON h.humanbeing_id = hu.humanbeing_id " +
                            "JOIN users u ON hu.user_id = u.user_id WHERE h.humanbeing_id = ?";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id.intValue());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                response.setId(id);
                response.setName(resultSet.getString("name"));
                response.setCoordinates(getCoordinates(resultSet.getInt("coordinates_id")));
                response.setCreationDate(convertTimeStampToZoned(resultSet.getTimestamp("creation_time")));
                response.setRealHero(resultSet.getBoolean("real_hero"));
                response.setHasToothpick(resultSet.getBoolean("has_toothpick"));
                response.setSoundtrackName(resultSet.getString("soundtrack"));
                response.setImpactSpeed(resultSet.getFloat("impact_speed"));
                response.setMood(Mood.valueOf(resultSet.getString("mood")));
                response.setWeaponType(WeaponType.valueOf(resultSet.getString("weapon_type")));
                response.setCar(getCar(resultSet.getInt("car_id")));
                response.setUsername(resultSet.getString("user_name"));
            }
            preparedStatement.close();


            return response;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Long getUserId(String userName) {
        long userId = 0;
        try {
            String query = "SELECT user_id FROM users WHERE user_name = ?";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, userName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getLong("user_id");
            }
            preparedStatement.close();
            return userId;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Set<String> getUserNameList() {
        Set<String> userList = new HashSet<>();
        try {
            String query = "SELECT user_name FROM users";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(resultSet.getString("user_name"));
            }
            preparedStatement.close();
            return userList;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public long userRegister(CurrentUserManager userManager, String password) {
        long id = -1;
        try {
            String username = userManager.getUserName();
            String salt = saltBuilder();
            String query = "INSERT INTO users VALUES (DEFAULT, ?, ?, 'USER', ?) RETURNING user_id";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, sha256encoding(pepper + password + salt));
            preparedStatement.setString(3, salt);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("user_id");
            }
            preparedStatement.close();
            if (id != -1) {
                String query2 = "INSERT INTO users_avatar VALUES (?, ?)";
                PreparedStatement preparedStatement2 = sqlConnection.getConnection().prepareStatement(query2);
                preparedStatement2.setLong(1, id);
                preparedStatement2.setString(2, userManager.getUserAvatar());

                int affectedRows2 = preparedStatement2.executeUpdate();
                if (affectedRows2 == 0) {
                    throw new ApplicationException(getLog("user_not_registered").replace("name", username));
                }
            }

        } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
        return id;
    }

    public ROLES getUserRole(String username) {
        ROLES role = ROLES.GUEST;
        try {
            String query = "SELECT role FROM users WHERE user_name = ?";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role = ROLES.valueOf(resultSet.getString("role"));
            }
            preparedStatement.close();
            return role;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean checkUserPassword(String username, String password) {
        String passwordHash = sha256encoding(pepper + password + getSalt(username));
        return passwordHash.equals(getPassword(username));
    }

    private String getSalt(String userName) {
        Long id = getUserId(userName);
        String salt = "";
        try {
            String query = "SELECT salt FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id.intValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                salt = (resultSet.getString("salt"));
            }
            preparedStatement.close();
            return salt;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private String getPassword(String userName) {
        String pass = "";
        try {
            String query = "SELECT password FROM users WHERE user_name = ?";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, userName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pass = (resultSet.getString("password"));
            }
            preparedStatement.close();
            return pass;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean isHumanBeingBelongsToUser(Long humanBeingId, Long userId) {
        try {
            String query = "SELECT humanbeing_id FROM humantouser WHERE user_id = ?";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (humanBeingId.equals(resultSet.getLong("humanbeing_id"))) {
                    return true;
                }
            }
            preparedStatement.close();
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public SQLConnection getSqlConnection() {
        return sqlConnection;
    }

    public Set<HumanBeingResponseDTO> getUpdatedDataSet() {
        updateDataSet();
        return dataSet;
    }

    public Set<HumanBeingResponseDTO> getDataSet() {
        return dataSet;
    }

    public void setDataSet(Set<HumanBeingResponseDTO> dataSet) {
        this.dataSet = dataSet;
    }

    public CurrentUserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(CurrentUserManager userManager) {
        this.userManager = userManager;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
