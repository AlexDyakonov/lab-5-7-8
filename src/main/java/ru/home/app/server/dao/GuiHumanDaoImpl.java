package ru.home.app.server.dao;

import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.authentication.ROLES;
import ru.home.app.server.db.SQLDataBaseProvider;
import ru.home.app.server.model.User;
import ru.home.app.server.model.dto.HumanBeingRequestDTO;
import ru.home.app.server.model.dto.HumanBeingResponseDTO;
import ru.home.app.server.sql.SQLConnection;
import ru.home.app.util.LANGUAGE;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GuiHumanDaoImpl implements GuiHumanDao {
    private final SQLConnection sqlConnection = new SQLConnection();
    private final SQLDataBaseProvider source = new SQLDataBaseProvider(sqlConnection);

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
        return SQLMethods.getAllUsers(sqlConnection);
    }


    @Override
    public Set<String> getUserNameList() {
        return getAllUsers().stream().map(User::getUserName).collect(Collectors.toSet());
    }

    @Override
    public void userRegister(String username, String password) {
        source.userRegister(username, password);
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
