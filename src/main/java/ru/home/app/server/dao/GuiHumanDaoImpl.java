package ru.home.app.server.dao;

import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.authentication.ROLES;
import ru.home.app.server.db.SQLDataBaseProvider;
import ru.home.app.server.model.User;
import ru.home.app.server.model.dto.HumanBeingRequestDTO;
import ru.home.app.server.model.dto.HumanBeingResponseDTO;
import ru.home.app.server.sql.SQLConnection;
import ru.home.app.util.LANGUAGE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.home.app.util.Parser.stringToRole;

public class GuiHumanDaoImpl implements GuiHumanDao {
    private final SQLConnection sqlConnection = new SQLConnection();
    private CurrentUserManager userManager;
    private final SQLDataBaseProvider source = new SQLDataBaseProvider(sqlConnection, userManager);

    public GuiHumanDaoImpl(CurrentUserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public HumanBeingResponseDTO getHumanById(Long id) {
        return null;
    }

    @Override
    public List<HumanBeingResponseDTO> getAllHuman() {
        return null;
    }

    @Override
    public Long createHuman(HumanBeingRequestDTO human) {
        return null;
    }

    @Override
    public void deleteHumanById(Long id) {

    }

    @Override
    public HumanBeingResponseDTO updateHuman(HumanBeingRequestDTO newHuman, Long id) {
        return null;
    }

    @Override
    public String info() {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> output = new ArrayList<>();
        long id;
        String userName;
        ROLES role;
        try {
            String query = "SELECT u.user_id, u.user_name, u.role, ua.user_avatar FROM users u JOIN users_avatar ua ON u.user_id = ua.user_id";
            PreparedStatement preparedStatement = sqlConnection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("user_id");
                userName = resultSet.getString("user_name");
                role = stringToRole(resultSet.getString("role"));
                String avatar = resultSet.getString("user_avatar");
                output.add(new User(id, userName, role, avatar));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return output;
    }


    @Override
    public Set<String> getUserNameList() {
        return getAllUsers().stream().map(User::getUserName).collect(Collectors.toSet());
    }

    @Override
    public long userRegister(CurrentUserManager userManager, String password) {
        return source.userRegister(userManager, password);
    }

    @Override
    public boolean checkUserPassword(String username, String password) {
        return source.checkUserPassword(username, password);
    }

    @Override
    public void setUserManager(CurrentUserManager currentUserManager) {

    }

    @Override
    public void setRole(String username, ROLES role) {

    }

    @Override
    public void setUserName(String userName) {

    }

    @Override
    public void setLanguage(LANGUAGE language) {
    }
}
