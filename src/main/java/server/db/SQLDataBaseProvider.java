package server.db;

import server.exception.ApplicationException;
import server.model.Car;
import server.model.Coordinates;
import server.model.Mood;
import server.model.WeaponType;
import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;
import server.sql.SQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

import static util.Parser.convertTimeStampToZoned;
import static util.Parser.convertZonedDateTimeToTimeStamp;

public class SQLDataBaseProvider {
    private final SQLConnection sqlConnection;

    public SQLDataBaseProvider(SQLConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public Long getUserName(String userName) {
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

    private String getCarName(int id) {
        String carName = null;
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
        if (getCarName(id) == null) {
            return null;
        } else {
            return new Car(getCarName(id), getCarCool(id));
        }
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

    public int setCoordinates(Coordinates coordinates) {
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

    public int setCar(Car car) {
        int carId = 0;
        try {
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

    public HumanBeingResponseDTO getHumanBeing(Long id) {
        HumanBeingResponseDTO response = new HumanBeingResponseDTO();
        try {
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

    public Long setHumanBeing(HumanBeingRequestDTO request) {
        long id = 0L;
        try {
            String query = "insert into humanbeing values (DEFAULT, ?, ?, TIMESTAMP ?, ?, ?, ?, ?, ?, ?, ?) RETURNING humanbeing_id";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, request.getName());
            preparedStatement.setInt(2, setCoordinates(request.getCoordinates()));
            preparedStatement.setTimestamp(3, convertZonedDateTimeToTimeStamp(ZonedDateTime.now()));
            preparedStatement.setBoolean(4, request.getRealHero());
            preparedStatement.setBoolean(5, request.getHasToothpick());
            preparedStatement.setFloat(6, request.getImpactSpeed());
            preparedStatement.setString(7, request.getSoundtrackName());
            preparedStatement.setString(8, request.getMood().toString());
            preparedStatement.setString(9, request.getWeaponType().toString());
            preparedStatement.setInt(10, setCar(request.getCar()));

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


    public static void main(String[] args) {
        SQLDataBaseProvider sqlDataBaseProvider = new SQLDataBaseProvider(new SQLConnection());
        System.out.println(sqlDataBaseProvider.getHumanBeing(1L));
    }
}
