package ru.home.app.server.dao;

import ru.home.app.server.authentication.ROLES;
import ru.home.app.server.model.User;
import ru.home.app.server.sql.SQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.home.app.util.Parser.stringToRole;

public class SQLMethods {
    static List<User> getAllUsers(SQLConnection sqlConnection) {
        List<User> output = new ArrayList<>();
        long id;
        String userName;
        ROLES role;
        try {
            String query = "SELECT * FROM users";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("user_id");
                userName = resultSet.getString("user_name");
                role = stringToRole(resultSet.getString("role"));
                output.add(new User(id, userName, role));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return output;
    }
}
