package server.controller;

import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;

import java.util.List;

public interface HumanController {

    // core commands

    HumanBeingResponseDTO getHumanById(Long id);
    List<HumanBeingResponseDTO> getAllHuman();
    Long createHuman(HumanBeingRequestDTO human);
    void deleteHumanById(Long id);
    HumanBeingResponseDTO updateHuman(HumanBeingRequestDTO newHuman, Long id );

    // additional commands

    String help();
    String info();
    void clear();
    void save(String fileName);
    HumanBeingResponseDTO max_by_impact_speed();
    List<HumanBeingResponseDTO> print_ascending();

    // additional composite commands

    Long addIfMax(HumanBeingRequestDTO request);
    Long addIfMin(HumanBeingRequestDTO request);
    int countByMood(String mood);

    boolean isImpactSpeedMax(HumanBeingRequestDTO dto);
    boolean isImpactSpeedMin(HumanBeingRequestDTO dto);
}
