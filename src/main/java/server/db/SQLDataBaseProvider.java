package server.db;

import server.exception.ApplicationException;
import server.model.Car;
import server.model.Coordinates;
import server.sql.SQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

//    public static void main(String[] args) {
//        SQLDataBaseProvider sqlDataBaseProvider = new SQLDataBaseProvider(new SQLConnection());
//        System.out.println(sqlDataBaseProvider.getCoordinates(1));
//    }
}
