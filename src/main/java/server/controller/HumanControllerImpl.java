package server.controller;

import server.authentication.ROLES;
import server.authentication.UserManager;
import server.exception.ValidationException;
import server.model.Mood;
import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;
import server.services.HumanService;
import server.services.HumanServiceImpl;
import server.validation.Validation;
import util.LANGUAGE;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static server.services.LoggerManager.setupLogger;
import static server.validation.Validation.validateFileWrite;
import static util.Message.getError;
import static util.Message.getWarning;

/**
 * The type Human controller.
 */
public class HumanControllerImpl implements HumanController {
    private static final Logger logger = Logger.getLogger(HumanControllerImpl.class.getName());
    private final HumanService service;
    private LANGUAGE language;

    /**
     * Instantiates a new Human controller.
     */

    public HumanControllerImpl() {
        this.service = new HumanServiceImpl();
        service.setLanguage(language);
        setupLogger(logger);
    }

    @Override
    public HumanBeingResponseDTO getHumanById(Long id) {
        if (id <= 0) {
            throw new ValidationException(getError("id_more_than_zero", language));
        }
        return service.getHumanById(id);
    }

    @Override
    public List<HumanBeingResponseDTO> getAllHuman() {
        return service.getAllHuman();
    }

    @Override
    public Long createHuman(HumanBeingRequestDTO human) {
        System.out.println(human);
        if (!Validation.validateRequestDTO(human)) {
            throw new ValidationException(getError("request_not_validated", language));
        }
        return service.createHuman(human);
    }

    @Override
    public void deleteHumanById(Long id) {
        if (id <= 0) {
            throw new ValidationException(getError("id_more_than_zero", language));
        }
        if (getHumanById(id) == null) {
            logger.warning(getWarning("user_not_found", LANGUAGE.EN));
        }
        service.deleteHumanById(id);
    }

    @Override
    public HumanBeingResponseDTO updateHuman(HumanBeingRequestDTO newHuman, Long id) {
        if (!Validation.validateRequestDTO(newHuman)) {
            throw new ValidationException(getError("request_not_validated", language));
        }
        return service.updateHuman(newHuman, id);
    }

    @Override
    public String info() {
        return service.info();
    }

    @Override
    public void clear() {
        service.clear();
    }

    @Override
    public void clearAll() {
        service.clearAll();
    }

    @Deprecated
    @Override
    public void save(String fileName, LANGUAGE language) {
        validateFileWrite(new File(fileName), language);
        service.save(fileName);
    }

    @Override
    public HumanBeingResponseDTO max_by_impact_speed() {
        return service.max_by_impact_speed();
    }

    @Override
    public List<HumanBeingResponseDTO> print_ascending() {
        return service.print_ascending();
    }

    @Override
    public int countByMood(String mood) {
        switch (mood) {
            case "SORROW":
                return service.countByMood(Mood.SORROW);
            case "GLOOM":
                return service.countByMood(Mood.GLOOM);
            case "APATHY":
                return service.countByMood(Mood.APATHY);
            case "CALM":
                return service.countByMood(Mood.CALM);
            case "RAGE":
                return service.countByMood(Mood.RAGE);
            default:
                throw new ValidationException(getError("mood_not_exist", language));
        }
    }

    @Override
    public boolean isImpactSpeedMax(HumanBeingRequestDTO dto) {
        return service.isImpactSpeedMax(dto);
    }

    @Override
    public boolean isImpactSpeedMin(HumanBeingRequestDTO dto) {
        return service.isImpactSpeedMin(dto);
    }

    @Override
    public void setLanguage(LANGUAGE language) {
        this.language = language;
        service.setLanguage(language);
    }

    @Override
    public Set<String> getUserNameList() {
        return service.getUserNameList();
    }

    @Override
    public void userRegister(String username, String password) {
        if (username.trim().equals("")) {
            throw new ValidationException(""); //TODO message
        }
        service.userRegister(username, password);
    }

    @Override
    public boolean checkUserPassword(String username, String password) {
        if (username.trim().equals("")) {
            throw new ValidationException(""); //TODO message
        }
        return service.checkUserPassword(username, password);
    }

    @Override
    public ROLES getUserRole(String userName) {
        return service.getUserRole(userName);
    }

    @Override
    public void setUserName(String userName) {
        service.setUserName(userName);
    }

    @Override
    public Long getUserId(String userName) {
        return service.getUserId(userName);
    }

    @Override
    public void setUserManager(UserManager userManager) {
        service.setUserManager(userManager);
    }

    @Override
    public void setRole(String username, ROLES role) {
        service.setRole(username, role);
    }
}
