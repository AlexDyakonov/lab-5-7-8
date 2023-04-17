package server.controller;

import server.exception.FileException;
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

import static client.ui.ConsoleColors.error;
import static server.validation.Validation.validateFileWrite;
import static util.Message.getError;

/**
 * The type Human controller.
 */
public class HumanControllerImpl implements HumanController {

    private final HumanService service;
    private LANGUAGE language;

    /**
     * Instantiates a new Human controller.
     *
     * @param fileName the file name
     */
    public HumanControllerImpl(String fileName) {
        this.service = new HumanServiceImpl(fileName);
        service.setLanguage(language);
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
        service.deleteHumanById(id);
    }

    @Override
    public HumanBeingResponseDTO updateHuman(HumanBeingRequestDTO newHuman, Long id) {
        if (id <= 0) {
            throw new ValidationException(getError("id_more_than_zero", language));
        }
        if (!Validation.validateRequestDTO(newHuman)) {
            throw new ValidationException(getError("request_not_validated", language));
        }
        return service.updateHuman(newHuman, id);
    }

    @Override
    public String help() {
        return service.help();
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
    public Long addIfMax(HumanBeingRequestDTO request) {
        if (!Validation.validateRequestDTO(request)) {
            throw new ValidationException(getError("request_not_validated", language));
        }
        return service.addIfMax(request);
    }

    @Override
    public Long addIfMin(HumanBeingRequestDTO request) {
        if (!Validation.validateRequestDTO(request)) {
            throw new ValidationException(getError("request_not_validated", language));
        }
        return service.addIfMin(request);
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
    public LANGUAGE getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(LANGUAGE language) {
        this.language = language;
        service.setLanguage(language);
    }
}
