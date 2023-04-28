package server.db;

import server.exception.ApplicationException;
import server.model.Car;
import server.model.Coordinates;
import server.model.Mood;
import server.model.WeaponType;
import server.model.dto.HumanBeingResponseDTO;
import server.sql.SQLConnection;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

import static server.services.LoggerManager.setupLogger;
import static util.Message.getLog;
import static util.Parser.convertTimeStampToZoned;


public class SQLDataBaseProvider {
    private static final Logger logger = Logger.getLogger(SQLDataBaseProvider.class.getName());

    static {
        setupLogger(logger);
    }

    private final SQLConnection sqlConnection;
    private final LocalDateTime creationDate;
    private final String pepper = "*63&^mVLC(#";
    private Set<HumanBeingResponseDTO> dataSet;


    public SQLDataBaseProvider(SQLConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
        this.dataSet = loadDataBase();
        this.creationDate = LocalDateTime.now();
    }

    private static String saltBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            stringBuilder.append((char) new Random().nextInt(33, 126));
        }
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
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new ApplicationException("Не удалось закодироваться...");
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dbSet;
    }

    public Set<HumanBeingResponseDTO> updateDataSet() {
        dataSet = loadDataBase();
        return dataSet;
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
            throw new RuntimeException(e);
        }
        return false;
    }

    public HumanBeingResponseDTO getHumanBeingById(Long id) {
        if (!findHumanById(id)) {
            return null;
        }
        try {
            HumanBeingResponseDTO response = new HumanBeingResponseDTO();
            String query = "SELECT * FROM humanbeing WHERE humanbeing_id = ?";
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
                response.setImpactSpeed(resultSet.getFloat("impact_speed"));
                response.setMood(Mood.valueOf(resultSet.getString("mood")));
                response.setWeaponType(WeaponType.valueOf(resultSet.getString("weapon_type")));
                response.setCar(getCar(resultSet.getInt("car_id")));
            }
            preparedStatement.close();
            return response;
        } catch (SQLException e) {
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
            throw new RuntimeException(e);
        }
    }

    public void userRegister(String username, String password) {
        try {
            String salt = saltBuilder();
            String query = "INSERT INTO users VALUES (DEFAULT, ?, ?, 'USER', ?)";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, sha256encoding(pepper + password + salt));
            preparedStatement.setString(3, salt);


            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new ApplicationException("Не удалось очистить базу данных");
            }
            preparedStatement.close();
        } catch (SQLException e) {
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
            throw new RuntimeException(e);
        }
    }

    public SQLConnection getSqlConnection() {
        return sqlConnection;
    }

    public Set<HumanBeingResponseDTO> getDataSet() {
        updateDataSet();
        return dataSet;
    }

    public void setDataSet(Set<HumanBeingResponseDTO> dataSet) {
        this.dataSet = dataSet;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
