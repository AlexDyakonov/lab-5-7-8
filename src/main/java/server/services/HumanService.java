package server.services;

import server.model.Mood;
import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;

import java.util.List;

public interface HumanService {
    HumanBeingResponseDTO getHumanById(Long id);
    List<HumanBeingResponseDTO> getAllHuman();
    Long createHuman(HumanBeingRequestDTO human);
    void deleteHumanById(Long id);
    HumanBeingResponseDTO updateHuman(HumanBeingRequestDTO newHuman, Long id );

    String help();
    String info();
    void clear();
    void save(String fileName);
    HumanBeingResponseDTO max_by_impact_speed();
    List<HumanBeingResponseDTO> print_ascending();

    Long addIfMax(HumanBeingRequestDTO request);
    Long addIfMin(HumanBeingRequestDTO request);
    int countByMood(Mood mood);

    boolean isImpactSpeedMax(HumanBeingRequestDTO dto);
    boolean isImpactSpeedMin(HumanBeingRequestDTO dto);
}
