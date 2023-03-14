package server.mapper;

import server.model.HumanBeingModel;
import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;

public class HumanBeingMapper {

    public HumanBeingResponseDTO fromModelToResponse(HumanBeingModel model) {
        HumanBeingResponseDTO responseDTO = new HumanBeingResponseDTO();
        responseDTO.setName(model.getName());
        responseDTO.setCoordinates(model.getCoordinates());
        responseDTO.setCreationDate(model.getCreationDate());
        responseDTO.setRealHero(model.getRealHero());
        responseDTO.setHasToothpick(model.getHasToothpick());
        responseDTO.setImpactSpeed(model.getImpactSpeed());
        responseDTO.setSoundtrackName(model.getSoundtrackName());
        responseDTO.setWeaponType(model.getWeaponType());
        responseDTO.setMood(model.getMood());
        responseDTO.setCar(model.getCar());
        return responseDTO;
    }

    public HumanBeingModel fromRequestToModel(HumanBeingRequestDTO dto) {
        HumanBeingModel model = new HumanBeingModel();
        model.setName(dto.getName());
        model.setCoordinates(dto.getCoordinates());
        model.setRealHero(dto.getRealHero());
        model.setHasToothpick(dto.getHasToothpick());
        model.setImpactSpeed(dto.getImpactSpeed());
        model.setSoundtrackName(dto.getSoundtrackName());
        model.setWeaponType(dto.getWeaponType());
        model.setMood(dto.getMood());
        model.setCar(dto.getCar());
        return model;
    }

    public HumanBeingRequestDTO fromStringToRequest(String line) {
        return new HumanBeingRequestDTO();
    }
}

/*
Model
private String id = UUID.randomUUID().toString();
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Boolean realHero; //Поле не может быть null
    private Boolean hasToothpick; //Поле не может быть null
    private Float impactSpeed; //Поле не может быть null
    private String soundtrackName; //Поле не может быть null
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле не может быть null
    private Car car; //Поле может быть null

  DTO
      private String name;
    private Coordinates coordinates;
    private Boolean realHero;
    private Boolean hasToothpick;
    private Float impactSpeed;
    private String soundtrackName;
    private WeaponType weaponType;
    private Mood mood;
    private Car car;

 */
