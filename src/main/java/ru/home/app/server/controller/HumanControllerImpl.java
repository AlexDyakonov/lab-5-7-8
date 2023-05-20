package ru.home.app.server.controller;

import ru.home.app.server.authentication.CurrentUserManager;
import ru.home.app.server.authentication.ROLES;
import ru.home.app.server.exception.ValidationException;
import ru.home.app.server.model.Mood;
import ru.home.app.server.model.User;
import ru.home.app.server.model.dto.HumanBeingRequestDTO;
import ru.home.app.server.model.dto.HumanBeingResponseDTO;
import ru.home.app.server.model.dto.HumanBeingResponseDTOwithUsers;
import ru.home.app.server.services.HumanService;
import ru.home.app.server.services.HumanServiceImpl;
import ru.home.app.server.validation.Validation;
import ru.home.app.util.LANGUAGE;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static ru.home.app.server.services.LoggerManager.setupLogger;
import static ru.home.app.server.validation.Validation.validate;
import static ru.home.app.server.validation.Validation.validateFileWrite;
import static ru.home.app.util.Message.getError;
import static ru.home.app.util.Message.getWarning;

/**
 * The type Human controller.
 */
public class HumanControllerImpl implements HumanController {
    private static final Logger logger = Logger.getLogger(HumanControllerImpl.class.getName());
    private final HumanService service;
    private final CurrentUserManager userManager;
    private LANGUAGE language;

    /**
     * Instantiates a new Human controller.
     */

    public HumanControllerImpl(CurrentUserManager userManager) {
        this.userManager = userManager;
        this.service = new HumanServiceImpl(userManager);
        service.setLanguage(language);
        setupLogger(logger);
    }


    @Override
    public HumanBeingResponseDTO getHumanById(Long id) {
        if (id <= 0) {
            throw new ValidationException(getError("id_more_than_zero", language));
        }
        validate(id, Validation::validateId, "Message"); // TODO сделать тут полноценную валидацию на все методы
        return service.getHumanById(id);
    }

    @Override
    public List<HumanBeingResponseDTO> getAllHuman() {
        return service.getAllHuman();
    }

    @Override
    public List<HumanBeingResponseDTOwithUsers> getAllHumanWithUsers() {
        return service.getAllHumanWithUsers();
    }

    @Override
    public Long createHuman(HumanBeingRequestDTO human) {
        validate(human, Validation::validateRequestDTO, "Error validation");
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
        return switch (mood) {
            case "SORROW" -> service.countByMood(Mood.SORROW);
            case "GLOOM" -> service.countByMood(Mood.GLOOM);
            case "APATHY" -> service.countByMood(Mood.APATHY);
            case "CALM" -> service.countByMood(Mood.CALM);
            case "RAGE" -> service.countByMood(Mood.RAGE);
            default -> throw new ValidationException(getError("mood_not_exist", language));
        };
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
    public Long addIfMax(HumanBeingRequestDTO request) {
        return service.addIfMax(request);
    }

    @Override
    public Long addIfMin(HumanBeingRequestDTO request) {
        return service.addIfMin(request);
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
    public long userRegister(CurrentUserManager userManager, String password) {
        validate(userManager.getUserName(), Validation::validateString, "message");
        return service.userRegister(userManager, password);
    }

    @Override
    public boolean checkUserPassword(String username, String password) {
        validate(username, Validation::validateString, "Message");
        validate(password, Validation::validatePassword, "Message");
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
    public void setUserManager(CurrentUserManager currentUserManager) {
        service.setUserManager(currentUserManager);
    }

    @Override
    public void setRole(String username, ROLES role) {
        service.setRole(username, role);
    }

    @Override
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }
}
