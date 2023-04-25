package server.dao;

import server.model.Mood;
import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;
import util.LANGUAGE;

import java.util.List;

public class HumanDaoPostgresImpl implements HumanDao {
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
    public void clear() {

    }

    @Override
    public void save(String fileName) {

    }

    @Override
    public HumanBeingResponseDTO max_by_impact_speed() {
        return null;
    }

    @Override
    public List<HumanBeingResponseDTO> print_ascending() {
        return null;
    }

    @Override
    public Long addIfMax(HumanBeingRequestDTO request) {
        return null;
    }

    @Override
    public Long addIfMin(HumanBeingRequestDTO request) {
        return null;
    }

    @Override
    public int countByMood(Mood mood) {
        return 0;
    }

    @Override
    public boolean isImpactSpeedMax(HumanBeingRequestDTO dto) {
        return false;
    }

    @Override
    public boolean isImpactSpeedMin(HumanBeingRequestDTO dto) {
        return false;
    }

    @Override
    public void setLanguage(LANGUAGE language) {

    }
}
